/**
 * @file rgb.h
 * @author Gianmarco Pettinato
 * @date 20/7/2018
 * @class RGB
 * @brief this class uses the as base class CIExyz
 * and stores a color in RGB rappresentation
 */
package kalk.model.color;

import java.util.Vector;

import java.util.Arrays;

import kalk.model.IllegalColorException;

import kalk.model.color.Color;
import kalk.model.factory.ColorFactory;

public class RGB extends CIExyz
{
	private static final RGB local = new RGB();
	@SuppressWarnings("unused")
	private static final boolean factory = ColorFactory.addColorFactory("RGB", local);
	private int[] sRGB = new int[3];
	private static double[][] CIE_RGB= new double[][]{{3.2404542,-1.5371385,-0.4985314},
														{-0.9692660,1.8760108,0.0415560},
														{0.0556434,-0.2040259,1.0572252}}; //contains matrix to transforms CIExyz color representation to sRGB
	private static double[][] RGB_CIE = new double[][]{{0.4124564, 0.3575761, 0.1804375},
														{0.2126729, 0.7151522, 0.0721750},
														{0.0193339, 0.1191920, 0.9503041}};//contains matrix to transforms sRGB color representation to CIExyz
	private static final int lower_limit = 0;
	private static final int upper_limit = 255;
	private static final int componets = 3;
	private static String[] implementedMethods={"negate","mix","divide"};

	//Contructors

	public RGB()
	{
		super();
		sRGB[0]=0;
		sRGB[1]=0;
		sRGB[2]=0;
	}

	public RGB(Color c) throws IllegalColorException
	{
		super(c);
		setComponents(CieXyz2rgb(c.getCIE().getComponents()));
	}

	public RGB(RGB c) throws IllegalColorException
	{
		super(c);
		setComponents(c.getComponents());
	}

	public RGB(int t_r, int t_g, int t_b) throws IllegalColorException
	{
		if(t_r>upper_limit || t_r<lower_limit ||
				t_g>upper_limit || t_g<lower_limit ||
				t_b>upper_limit || t_b<lower_limit)
			throw new IllegalColorException("values out of boundaries");
		sRGB[0]=t_r;
		sRGB[1]=t_g;
		sRGB[2]=t_b;
	}

	public Color getNewIstance()
	{
		return new RGB();
	}

	/**
	 * @brief RGB.getNumberOfComponets
	 * @return int
	 */
	public int getNumberOfComponets()
	{
		return componets;
	}

	/**
	 * @brief setComponents set the components inside the object
	 * @param componets
	 */

	public void setComponents(Vector<Double> componets) throws IllegalColorException
	{
		super.setComponents(rgb2CieXyz(componets));
		if(componets.elementAt(0)<lower_limit || componets.elementAt(0)>upper_limit ||
				componets.elementAt(1)<lower_limit || componets.elementAt(1)>upper_limit ||
				componets.elementAt(2)<lower_limit || componets.elementAt(2)>upper_limit)
			throw new IllegalColorException("values out of boundaries");
		sRGB[0]=(componets.elementAt(0).intValue());
		sRGB[1]=(componets.elementAt(1).intValue());
		sRGB[2]=(componets.elementAt(2).intValue());

	}

	/**
	 * @brief negate
	 * @return return a new Color object with a new complementary color
	 */
	public Color negate() throws IllegalColorException
	{
		return new RGB (255-sRGB[0], 255-sRGB[1], 255-sRGB[2]);
	}

	/**
	 * @brief RGB.mix
	 * @param Color* t_c
	 * @return a new Color object with the mixed Colors
	 */
	public Color mix(Color t_c) throws IllegalColorException
	{
		RGB to_mix = new RGB(t_c);
		int r =((to_mix.sRGB[0]+sRGB[0])/2);
		int g =((to_mix.sRGB[1]+sRGB[1])/2);
		int b =((to_mix.sRGB[2]+sRGB[2])/2);
		return new RGB(r,g,b);
	}

	/**
	 * @brief RGB.getCIE converts RGB value to CIExyz
	 * @param int t_r
	 * @param int t_g
	 * @param int t_b
	 * @return CIExyz
	 */

	public CIExyz getCIE(int t_r, int t_g, int t_b) throws IllegalColorException
	{
		Double[] values = new Double[]{(double)t_r,(double)t_g,(double)t_b};
		Vector<Double> rgbRappresentation = new Vector<Double>(Arrays.asList(values));
		Vector<Double> cierap = rgb2CieXyz(rgbRappresentation);
		return new CIExyz(cierap.elementAt(0),cierap.elementAt(1),cierap.elementAt(2));
	}

	/**
	 * @brief RGB.getComponent returns component in RGB class;
	 * @return Vector<Double>
	 */
	public Vector<Double> getComponents()
	{
		Double[] dsRGB = new Double[]{(double)sRGB[0],(double)sRGB[1],(double)sRGB[2]};
		return new Vector<Double>(Arrays.asList(dsRGB));
	}

	/**
	 * @brief division  new RGB object with value divided
	 * @param int div
	 * @return RGB
	 */
	public RGB division( int div) throws IllegalColorException
	{
		return new RGB(sRGB[0]/div,sRGB[1]/div,sRGB[2]/div);
	}

	public Vector<String> availableOperations()
	{
		return new Vector<String>(Arrays.asList(implementedMethods));
	}



	//PRIVATE METHODS

	private Vector<Double> CieXyz2rgb(Vector<Double> components)
	{
		Vector<Double> RGBrap = new Vector<Double>(3);
		for(int i=0; i<3; i++)
		{
			for(int j=0; j<3; j++)
			{
				double tomultiply = components.elementAt(j);
				RGBrap.set(i,(CIE_RGB[i][j]*tomultiply)+RGBrap.elementAt(i).doubleValue());
			}
			RGBrap.set(i,RGBrap.elementAt(i)*255);
		}
		return RGBrap;
	}

	private Vector<Double> rgb2CieXyz(Vector<Double> components)
	{

		Vector<Double> cierap = new Vector<Double>(3);
		for(int i=0; i<3; i++)
		{
			for(int j=0; j<3; j++)
			{
				double tomultiply = components.elementAt(j)/255;
				cierap.set(j,(RGB_CIE[i][j]*tomultiply)+cierap.elementAt(j).doubleValue());
			}
		}
		return cierap;
	}
}
