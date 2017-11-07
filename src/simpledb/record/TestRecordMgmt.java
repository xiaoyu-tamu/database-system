package simpledb.record;
import simpledb.tx.*;
import simpledb.server.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestRecordMgmt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Schema esch = new Schema();
		esch.addIntField("cid");
		esch.addStringField("title", 20);
		esch.addIntField("deptid");
		TableInfo eti = new TableInfo("ecourse",esch);
		for(String fld : eti.schema().fields()) {
			int offset = eti.offset(fld);
			System.out.println(fld + " has offset " + offset);
		}
	}
}
