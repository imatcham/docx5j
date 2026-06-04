package com.jumbletree.docx5j.xlsx.builders;

import static org.junit.Assert.*;

import javax.xml.bind.JAXBException;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.junit.Test;
import org.xlsx4j.sml.Cell;
import org.xlsx4j.sml.STCellType;

public class CellBuilderTest {

	@Test
	public void testNaNsHandledAppropriately() throws InvalidFormatException, Docx4JException, JAXBException {
		CellBuilder cells = new WorkbookBuilder()
			.appendSheet()
				.nextRow()
					.nextCell();
		
		cells.value(Double.NaN);
		
		//Excel doesn't know about NaN so the cell should NOT be a number cell
		Cell cell = cells.cell;
		
		assertNotEquals(STCellType.N, cell.getT());
		assertNotEquals(String.valueOf(Double.NaN), cell.getV());
		
		//TDD
		//What we actually want is a formula cell with NA() in it
		assertEquals(STCellType.E, cell.getT());
		assertEquals("#N/A", cell.getV());
		assertEquals("NA()", cell.getF().getValue());
	}

}
