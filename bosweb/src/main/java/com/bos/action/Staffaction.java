package com.bos.action;

import com.bos.action.base.BaseAction;
import com.bos.domain.Staff;
import com.bos.service.IstaffService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class Staffaction extends BaseAction<Staff> {
	@Autowired
	private IstaffService istaffService;
	private int page;
	private int rows;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	//	添加取派员的方法
	public String add() {
		istaffService.save(moudele);
		return "list";
	}

	public String pagequery() throws Exception {
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
//		pageBean.setDetachedCriteria(detachedCriteria);
		istaffService.pageQuery(pageBean);
//		将pagebean转成json,通过输出流写入到页面
//		Gson gson = new Gson();
//		gson.toJson(pageBean);
//		sf里面的json转换工具，用于转换单一的json对象
//		JsonConfig jsonConfig = new JsonConfig();
//		用于排除那些属性不需要转成json
		javatojson(pageBean, new String[]{"currentPage", "detachedCriteria", "pageSize", "decidedzones"});
		return null;
	}

	private String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	//	执行这个方法需要当前用户具有staff-delete这个权限
	@RequiresPermissions("staff-delete")
	public String deleteBatch() throws Exception {
		istaffService.deleteBatch(ids);
		return "list";
	}

	public String edit() throws Exception {
// 注意，不可以直接更新moudle对象，因为传递过来的参数可能不全，更新会造成数据丢失
//		查数据，查原始数据后覆盖才是安全的做法
		Staff staff = istaffService.findbyid(moudele.getId());
//		对页面提交的数据进行覆盖
		staff.setName(moudele.getName());
		staff.setTelephone(moudele.getTelephone());
		staff.setHaspda(moudele.getHaspda());
		staff.setStandard(moudele.getStandard());
		staff.setStation(moudele.getStation());
		istaffService.update(staff);
		return "list";
	}


	public String listajax() throws Exception {
		List<Staff> staff = istaffService.findlistnotdelete();
		javatojson(staff, new String[]{"decidedzones"});
		return null;
	}
}
