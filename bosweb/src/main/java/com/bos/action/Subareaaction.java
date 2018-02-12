package com.bos.action;

import com.bos.action.base.BaseAction;
import com.bos.domain.Region;
import com.bos.domain.Subarea;
import com.bos.service.IsubareaService;
import com.bos.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletOutputStream;
import java.util.List;

@Controller
@Scope("prototype")
public class Subareaaction extends BaseAction<Subarea> {
	@Autowired
	private IsubareaService isubareaService;

	//添加分区
	public String add() {
		isubareaService.save(moudele);
		return "list";
	}


	public String pageQuery() throws Exception {
//		注意，这里获取的实际上是栈顶元素subarea对象
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		String addresskey = moudele.getAddresskey();
		if (StringUtils.isNotBlank(addresskey)) {
			detachedCriteria.add(Restrictions.like("addresskey", "%" + addresskey + "%"));
		}
		Region region = moudele.getRegion();
		if (region != null) {
			String province = region.getProvince();
//			String city = region.getCity();
//			String district = region.getDistrict();
			if (StringUtils.isNotBlank(province)) {
//				多表查询可以用表里的别名来进行查询，第一个参数是栈顶元素中关联的属性名称，r是别名，可以任意，province则是r类里的属性
				detachedCriteria.createAlias("region", "r");
				detachedCriteria.add(Restrictions.like("r.province", "%" + province + "%"));
			}
		}
		isubareaService.pagequery(pageBean);
		javatojson(pageBean, new String[]{"currentPage", "detachedCriteria", "pageSize", "decidedzone", "subareas"});
		return null;
	}


	public String exportXls() throws Exception {
//第一步：查询所有的分区数据
		List<Subarea> list = isubareaService.findAll();
		//第二步：使用POI将数据写到Excel文件中
		//在内存中创建一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建一个标签页
		HSSFSheet sheet = workbook.createSheet("分区数据");
		//创建标题行
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("分区编号");
		headRow.createCell(1).setCellValue("开始编号");
		headRow.createCell(2).setCellValue("结束编号");
		headRow.createCell(3).setCellValue("位置信息");
		headRow.createCell(4).setCellValue("省市区");

		for (Subarea subarea : list) {
//			这相当于一个通用的计数器
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getStartnum());
			dataRow.createCell(2).setCellValue(subarea.getEndnum());
			dataRow.createCell(3).setCellValue(subarea.getPosition());
			dataRow.createCell(4).setCellValue(subarea.getRegion().getName());
		}

		//第三步：使用输出流进行文件下载（一个流、两个头）

		String filename = "分区数据.xls";
		String contentType = ServletActionContext.getServletContext().getMimeType(filename);
		ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
		ServletActionContext.getResponse().setContentType(contentType);

		//获取客户端浏览器类型
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		filename = FileUtils.encodeDownloadFilename(filename, agent);
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename=" + filename);
		workbook.write(out);
		return null;
	}

	public String listajax() throws Exception {
		List<Subarea> listNotassociatiion = isubareaService.findListNotassociatiion();
		javatojson(listNotassociatiion, new String[]{"decidedzone", "region"});
		return null;
	}

	public String findlistBydecidedzoneId() throws Exception {
		List<Subarea> findlistbydecidedid = isubareaService.findlistbydecidedid(decidedzoneId);
		javatojson(findlistbydecidedid, new String[]{"decidedzone", "subareas"});
		return null;
	}

	public String findSubareasGroupByProvince() throws Exception {
//		查询区域分区分布图的数据
//		注意，如果数据是object，则格式是这样的
//		[["北京市",1],["天津市",1],["山西省",1]]
		List<Object[]> subareasGroup = isubareaService.findSubareasGroup();
		this.javatojson(subareasGroup, new String[]{});
		return null;
	}

	private String decidedzoneId;
}
