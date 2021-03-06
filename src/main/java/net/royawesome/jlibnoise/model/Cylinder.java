/* Copyright (C) 2011 Garrett Fleenor

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; either version 3.0 of the License, or (at
 your option) any later version.

 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public
 License (COPYING.txt) for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

 This is a port of libnoise ( http://libnoise.sourceforge.net/index.html ).  Original implementation by Jason Bevins

*/

package net.royawesome.jlibnoise.model;

import net.royawesome.jlibnoise.Utils;
import net.royawesome.jlibnoise.exception.NoModuleException;
import net.royawesome.jlibnoise.module.Module;

/**
 * Model that defines the surface of a cylinder.
 * 
 * @image html modelcylinder.png
 * 
 *        This model returns an output value from a noise module given the
 *        coordinates of an input value located on the surface of a cylinder.
 * 
 *        To generate an output value, pass the (angle, height) coordinates of
 *        an input value to the GetValue() method.
 * 
 *        This model is useful for creating: - seamless textures that can be
 *        mapped onto a cylinder
 * 
 *        This cylinder has a radius of 1.0 unit and has infinite height. It is
 *        oriented along the @a y axis. Its center is located at the origin.
 */
public class Cylinder {
	Module module;

	/**
	 * @param module The noise module that is used to generate the output
	 *            values.
	 */
	public Cylinder(Module mod) {
		this.module = mod;
	}

	/**
	 * Returns the noise module that is used to generate the output values.
	 * 
	 * @return A reference to the noise module.
	 * @pre A noise module was passed to the SetModule() method.
	 */
	public Module getModule() {
		return this.module;
	}

	/**
	 * Sets the noise module that is used to generate the output values.
	 * 
	 * @param module The noise module that is used to generate the output
	 *            values.
	 * 
	 *            This noise module must exist for the lifetime of this object,
	 *            until you pass a new noise module to this method.
	 */
	public void setModule(Module mod) {
		if (mod == null)
			throw new IllegalArgumentException("Mod cannot be null");
		this.module = mod;
	}

	/**
	 * Returns the output value from the noise module given the (angle, height)
	 * coordinates of the specified input value located on the surface of the
	 * cylinder.
	 * 
	 * @param angle The angle around the cylinder's center, in degrees.
	 * @param height The height along the @a y axis.
	 * @return The output value from the noise module.
	 * @pre A noise module was passed to the SetModule() method.
	 * 
	 *      This output value is generated by the noise module passed to the
	 *      SetModule() method.
	 * 
	 *      This cylinder has a radius of 1.0 unit and has infinite height. It
	 *      is oriented along the @a y axis. Its center is located at the
	 *      origin.
	 */
	double getValue(double angle, double height) {
		if (module == null)
			throw new NoModuleException();

		double x, y, z;
		x = Math.cos(angle * Utils.DEG_TO_RAD);
		y = height;
		z = Math.sin(angle * Utils.DEG_TO_RAD);
		return module.GetValue(x, y, z);

	}
}
