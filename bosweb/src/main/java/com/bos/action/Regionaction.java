package com.bos.action;

import com.bos.action.base.BaseAction;
import com.bos.domain.Region;
import com.bos.service.Iregionnservice;
import com.bos.utils.PinYin4jUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
public class Regionaction extends BaseAction<Region> {
	//	属性驱动，接收上传的文件,需要与前台的name属性一致
	private File regionFile;
	private String q;

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	@Autowired
	private Iregionnservice regonservice;

	public File getRegionFile() {
		return regionFile;
	}

	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}


	public String importxls() throws Exception {
		List<Region> regions = new ArrayList<>();
//		使用poi解析xml配置文件
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(regionFile));
		HSSFSheet sheetAt = hssfWorkbook.getSheetAt(0);
//		根据标签页名称来获得对象
		//		hssfWorkbook.getSheet("标签页")
		for (Row cells : sheetAt) {
//			读取每一行，获得行号，如果行号是0则不读取
			int rowNum = cells.getRowNum();
			if (rowNum == 0) {
				continue;
			}
//			读取列
			String id = cells.getCell(0).getStringCellValue();
			String province = cells.getCell(1).getStringCellValue();
			String city = cells.getCell(2).getStringCellValue();
			String district = cells.getCell(3).getStringCellValue();
			String postcode = cells.getCell(4).getStringCellValue();
			Region region = new Region(id, province, city, district, postcode, null, null, null);
			province = province.substring(0, province.length() - 1);
			city = city.substring(0, city.length() - 1);
			district = district.substring(0, district.length() - 1);
			String s = province + city + district;
			String[] headByString = PinYin4jUtils.getHeadByString(s);
			String join = StringUtils.join(headByString);
			String s1 = PinYin4jUtils.hanziToPinyin(city, "");
			region.setShortcode(join);
			region.setCitycode(s1);
//			保存region对象
			regions.add(region);
		}
//		不管循环多少次都只开启一个事物
		regonservice.saveBatch(regions);
		return null;
	}


	public String pagequery() throws Exception {
//      封装PageBean
		regonservice.pageQuery(pageBean);
//		net.sf.json.JSONException: There is a cycle in the hierarchy!
//		如果出现这种异常首先先判断是否死循环，如果死循环则排除可能互相引用的分区
		javatojson(pageBean, new String[]{"currentPage", "detachedCriteria", "pageSize", "subareas"});
		return null;
	}


	public String listajax() throws Exception {
//		List<Region> regions = regonservice.findall();
		List<Region> regions = null;
		if (StringUtils.isNotBlank(q)) {
			regions = regonservice.findbylist(q);
		} else {
			regions = regonservice.findall();
		}
		javatojson(regions, new String[]{"subareas"});
		return null;
	}
}
