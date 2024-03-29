/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atomrockets.marketpredictor.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import com.atomrockets.marketpredictor.beans.YahooDOHLCVARow;

/**
 * @author Aaron
 */
public class MarketRetriever {

	public static List<YahooDOHLCVARow> PVDParser(String url) {
		List<YahooDOHLCVARow> rowsFromYahooURL = null;
		
		try {
			URL ur = new URL(url);
			HttpURLConnection HUC = (HttpURLConnection) ur.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(HUC.getInputStream()));
			
			//System.out.println(reader.readLine());//reads the first line, it's just headers
			reader.readLine();//reads the first line, it's just headers
			
			//OpenCSV parser
			CSVReader csvReader = new CSVReader(reader, ',', '\"');
			ColumnPositionMappingStrategy<YahooDOHLCVARow> strategy = new ColumnPositionMappingStrategy<YahooDOHLCVARow>();
		    strategy.setType(YahooDOHLCVARow.class);
		    strategy.setColumnMapping(new String[]{"date","Open","High","Low","Close","Volume","AdjClose"});

		    CsvToBean<YahooDOHLCVARow> csv = new CsvToBean<YahooDOHLCVARow>();
		    rowsFromYahooURL = csv.parse(strategy, csvReader);
		    
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rowsFromYahooURL;
	}

	/**
	 * *Builds url for a user supplied # of startDaysAgo
	 * @param symbol    market or stock for which data is desired
	 * @param daysAgo  # of startDaysAgo to retrieve data
	 * @return  construed URL
	 */
	public static String getYahooURL(String symbol, int daysAgo) {
		LocalDate endDate = new LocalDate();
		LocalDate startDate = endDate.minusDays(daysAgo);
		//TODO remove old date code
		//GregorianCalendar calendarStart = new GregorianCalendar();
		//calendarStart.add(Calendar.DAY_OF_MONTH, -daysAgo);//this subtracts the number of startDaysAgo from todays date.  The add command changes the calendar object
		int a_startMonth, b_startDay, c_startYear;
		int d_endMonth, e_endDay, f_endYear; 
		a_startMonth = startDate.getMonthOfYear()-1;//Yahoo uses zero based month numbering, this gets the beginning dates month
		b_startDay = startDate.getDayOfMonth();//this gets beginning dates day
		c_startYear = startDate.getYear();//this gets beginning dates year

		GregorianCalendar calendarEnd = new GregorianCalendar();
		d_endMonth = endDate.getMonthOfYear()-1;//Yahoo uses zero based month numbering,this gets todays month
		e_endDay = endDate.getDayOfMonth();//this gets todays day of month
		f_endYear = endDate.getYear();//this gets todays year

		//System.out.println("month="+a_startMonth+" day="+b_startDay+" year="+c_startYear);

		String str = "http://ichart.finance.yahoo.com/table.csv?s="
				+ symbol.toUpperCase() + "&a=" + a_startMonth + "&b=" + b_startDay + "&c=" + c_startYear + "&g=d&d=" + d_endMonth + "&e=" + e_endDay
				+ "&f=" + f_endYear + "&ignore=.csv";
		System.out.println("          Using the following Yahoo URL:");
		System.out.println("          " + str);
		return str;
	}

	public static int getNumberOfDaysFromNow(LocalDate date){
		LocalDate today = new LocalDate(); //Variable with today's date

		//Check if today is weekend, if so adjust the date back until it isn't a weekend
		while(today.getDayOfWeek() > 5){
			today = today.minusDays(1);
		}
		return Days.daysBetween(date, today).getDays();
	}
}
