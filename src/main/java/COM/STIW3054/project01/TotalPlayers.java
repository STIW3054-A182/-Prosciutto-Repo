package COM.STIW3054.project01;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TotalPlayers {
	ChessURL[] url;
	LogURL buff;

	public TotalPlayers(ChessURL[] url, LogURL file) {
		// TODO Auto-generated constructor stub
		this.url = url;
		this.buff = file;
	}

	public void calculate() {
		String format = "| %-8s| %-8s|\n";
		String regexcategory = "((U\\d{1,4}G|U\\d{1,4})|JUNIOR)";
		String rgxLocation = "(\\d{1,5}\\s)(\\w+|\\w+\\s\\w+)(\\s\\d,\\d)";
		int total = 0;
		int grandroral = 0;
		System.out.println("----------------------");
		System.out.format(format, "Category", "Total");
		System.out.println("|---------|---------|");

		for (int g = 0; g < url.length; g++) {
			Matcher m = Pattern.compile(regexcategory).matcher(url[g].retiveURLContent());
			Matcher m1 = Pattern.compile(rgxLocation).matcher(url[g].retiveURLContent());
			total = 0;
			while (m.find()) {
				String location = m.group().trim();
				while (m1.find()) {
					total++;
					grandroral++;
				}
				if (!(total == 0.0)) {
					System.out.format(format, location,total);

				} else {
					buff.WriteLog(url[g].URLname());
				}

			}
		}

		System.out.format(format, "total", grandroral);

	}

}
