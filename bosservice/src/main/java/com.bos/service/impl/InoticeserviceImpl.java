package com.bos.service.impl;

import com.bos.dao.base.IdecidedzoneDao;
import com.bos.dao.base.InoticeDao;
import com.bos.dao.base.WorkbillDao;
import com.bos.domain.*;
import com.bos.service.Inoticeservice;
import com.bos.utils.Bosutils;
import com.bos.utils.crm.ICustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@Transactional
public class InoticeserviceImpl implements Inoticeservice {
	@Override
	public void save(Noticebill moudele) {
		User getlogin = Bosutils.getlogin();
		moudele.setUser(getlogin);
		inoticeDao.save(moudele);
//		保存对象之外还需要尝试自动分单
//		获取客户的地址
		String pickaddress = moudele.getPickaddress();
//		远程调用crm服务，根据地址进行查询
		String s = iCustomerService.findbydecidAddress(pickaddress);
		if (StringUtils.isNotBlank(s)) {
//			查询到定区ID，可以进行自动分单
			Decidedzone byid = idecidedzoneDao.findByid(s);
//			注意，查到的外键正好是byid这张表的主键，然后根据主键就可以获得一个定区的对象，里面包含了取派员的地址
			Staff staff = byid.getStaff();
			moudele.setStaff(staff);
//			设置分担类型为自动分单
			moudele.setOrdertype(Noticebill.ORDERTYPE_AUTO);
//			为取派员产生一个工单
			Workbill workbill = new Workbill();
//			追单次数
			workbill.setAttachbilltimes(0);
//			工单的创建时间
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
			workbill.setNoticebill(moudele);
			workbill.setPickstate(Workbill.PICKSTATE_NO);
//			备注信息
			workbill.setRemark(moudele.getRemark());
			workbill.setStaff(staff);
			workbill.setType(Workbill.TYPE_1);
			workbillDao.save(workbill);
//			调用短信平台
		} else {
			moudele.setOrdertype(Noticebill.ORDERTYPE_MAN);
		}
	}

	@Autowired
	private InoticeDao inoticeDao;
	@Autowired
	private ICustomerService iCustomerService;
	@Autowired
	private IdecidedzoneDao idecidedzoneDao;
	@Autowired
	private WorkbillDao workbillDao;
}
