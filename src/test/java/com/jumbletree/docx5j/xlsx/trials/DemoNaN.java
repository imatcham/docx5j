package com.jumbletree.docx5j.xlsx.trials;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.docx4j.openpackaging.exceptions.Docx4JException;

import com.jumbletree.docx5j.xlsx.XLSXFile;

public class DemoNaN {

	public static void main(String[] args) throws JAXBException, Docx4JException, IOException {
		XLSXFile file = new XLSXFile();
		
		//First sheet is created automatically
		file.getWorkbookBuilder()
			.getSheet(0)
			.nextRow()
				.nextCell()
					.value(1)
					.row()
				.nextCell()
					.value(Double.NaN)
					.row()
				.nextCell()
					.value(2)
					.row()
				.nextCell()
					.value(Double.POSITIVE_INFINITY)
					.row()
				.nextCell()
					.value(Double.NEGATIVE_INFINITY)
					.row()
				.nextCell()
					.value(3);
		
		File out = new File("DemoNaN.xlsx");
		file.save(out);
		Desktop.getDesktop().open(out);
	}

}
