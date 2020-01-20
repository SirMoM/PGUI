/**
 * 
 */
package de.pgui.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.sun.javafx.css.parser.LadderConverter;

/**
 * @author Noah Ruben
 *
 *
 * @created 20.01.2020
 */
public class Theme {

	private static final String backgroundColorPropName = "backgroundColor";
	private static final String backgroundHighlightColorPropName = "backgroundHighlightColor";
	private static final String outlineColorPropName = "outlineColor";
	private static final String outlineHighlightColorPropName = "outlineHighlightColor";
	private static final String textColorPropName = "textColor";
	private static final String textHighlightColorPropName = "textHighlightColor";

	private final int backgroundColor;
	private final int backgroundHighlightColor;
	private final int outlineColor;
	private final int outlineHighlightColor;
	private final int textColor;
	private final int textHighlightColor;

	/**
	 * @param backgroundColor
	 * @param backgroundHighlightColor
	 * @param outlineColor
	 * @param outlineHighlightColor
	 * @param textColor
	 * @param textHighlightColor
	 */
	public Theme(int backgroundColor, int backgroundHighlightColor, int outlineColor, int outlineHighlightColor,
			int textColor, int textHighlightColor) {
		this.backgroundColor = backgroundColor;
		this.backgroundHighlightColor = backgroundHighlightColor;
		this.outlineColor = outlineColor;
		this.outlineHighlightColor = outlineHighlightColor;
		this.textColor = textColor;
		this.textHighlightColor = textHighlightColor;
	}

	public static Theme loadTheme(final File file) {
		int backgroundColor;
		int backgroundHighlightColor;
		int outlineColor;
		int outlineHighlightColor;
		int textColor;
		int textHighlightColor;

		Properties properties = new Properties();
		if (file != null && file.exists() && file.isFile()) {
			File propertiesFile = file;
			try {
				properties.load(new FileInputStream(propertiesFile));

				backgroundColor = Integer.valueOf(properties.getProperty(backgroundColorPropName, null));
				backgroundHighlightColor = Integer
						.valueOf(properties.getProperty(backgroundHighlightColorPropName, null));
				outlineColor = Integer.valueOf(properties.getProperty(outlineColorPropName, null));
				outlineHighlightColor = Integer.valueOf(properties.getProperty(outlineHighlightColorPropName, null));
				textColor = Integer.valueOf(properties.getProperty(textColorPropName, null));
				textHighlightColor = Integer.valueOf(properties.getProperty(textHighlightColorPropName, null));

				return new Theme(backgroundColor, backgroundHighlightColor, outlineColor, outlineHighlightColor,
						textColor, textHighlightColor);

			} catch (IOException e) {
				return null;
			}
		} else {
			// TODO real LOG
			System.out.println("Could not load Theme from File" + file);
			return null;
		}

	}

	public static Theme loadTheme(final String fullFilePath) {
		File file = new File(fullFilePath);
		return loadTheme(file);
	}

	public void saveTheme(final File saveFile) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.setProperty(backgroundColorPropName, Integer.toString(this.backgroundColor));
		properties.setProperty(backgroundHighlightColorPropName, Integer.toString(this.backgroundHighlightColor));
		properties.setProperty(outlineColorPropName, Integer.toString(this.outlineColor));
		properties.setProperty(outlineHighlightColorPropName, Integer.toString(this.outlineHighlightColor));
		properties.setProperty(textColorPropName, Integer.toString(this.textColor));
		properties.setProperty(textHighlightColorPropName, Integer.toString(this.textHighlightColor));
		
		properties.store(new FileOutputStream(saveFile), null);
	}
}
