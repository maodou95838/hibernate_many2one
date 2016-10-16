package com.jackie.hibernate;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * ��hbm����ddl
 * @author Administrator
 *
 */
public class ExportDB4Jpa {

	public static void main(String[] args) {
		
		//Ĭ�϶�ȡhibernate.cfg.xml�ļ�
		Configuration cfg = new AnnotationConfiguration().configure();
		
		SchemaExport export = new SchemaExport(cfg);
		export.create(true, true);
	}
}
