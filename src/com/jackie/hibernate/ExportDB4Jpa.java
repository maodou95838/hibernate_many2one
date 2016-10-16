package com.jackie.hibernate;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * 将hbm生成ddl
 * @author Administrator
 *
 */
public class ExportDB4Jpa {

	public static void main(String[] args) {
		
		//默认读取hibernate.cfg.xml文件
		Configuration cfg = new AnnotationConfiguration().configure();
		
		SchemaExport export = new SchemaExport(cfg);
		export.create(true, true);
	}
}
