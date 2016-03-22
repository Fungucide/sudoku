
public class CheckandUtilities
{
	/**
	 * 
	 * @param map
	 *            Map of values
	 * @param values
	 *            check against
	 * @param x
	 *            x-cord
	 * @param y
	 *            y-cord
	 * @return if value appears in quadrant
	 */
	public static boolean quadrantContains(String[][] map, String values, int x, int y)
	{
		int Xquad = (int) ((x - (x % Math.sqrt(map[0].length))) / Math.sqrt(map[0].length));
		int Yquad = (int) ((y - (y % Math.sqrt(map[0].length))) / Math.sqrt(map[0].length));
		for (int X = 0; X < Math.sqrt(map[0].length); X++)
		{
			for (int Y = 0; Y < Math.sqrt(map[0].length); Y++)
			{
				// System.out.println("#Quad# Checking at:" +
				// (Math.sqrt(map[0].length * Xquad + X)) + " " +
				// (Math.sqrt(map[0].length * Yquad + Y)) + " : " +
				// map[map[0].length * Xquad + X][3 * Yquad + Y] + " for: " +
				// values);
				if (map[(int) (Math.sqrt(map[0].length) * Xquad + X)][(int) (Math.sqrt(map[0].length) * Yquad + Y)].equals(values))
				{
					// System.out.println("#Quad# Found: " + values + " at
					// location: " + (3 * Xquad + X) + " " + (3 * Yquad + Y));
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param map
	 *            Map of values
	 * @param values
	 *            check against
	 * @param x
	 *            x-cord
	 * @param y
	 *            y-cord
	 * @return if value appears in row/column
	 */
	public static boolean rowContains(String[][] map, String values, int x, int y)
	{
		for (int i = 0; i < map[0].length; i++)
		{
			// System.out.println("#Row# Checking at:" + i + " " + y + " : " +
			// map[i][y] + " for: " + values);
			if (map[i][y].equals(values))
			{
				// System.out.println("#Row# Found: " + values + " at location:
				// " + i + " " + y);
				return true;
			}
		}
		return false;
	}

	public static boolean columnContains(String[][] map, String values, int x, int y)
	{
		for (int i = 0; i < map[0].length; i++)
		{
			// System.out.println("#Column# Checking at:" + x + " " + i + " : "
			// + map[x][i] + " for: " + values);
			if (map[x][i].equals(values))
			{
				// System.out.println("#Column# Found: " + values + " at
				// location: " + x + " " + i);
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param map
	 *            Map of values
	 * @param values
	 *            list of possible values
	 * @param x
	 *            x-cord
	 * @param y
	 *            y-cord
	 * @return Lowest value that can fit
	 */
	public static String lowestValue(String[][] map, String[] values, int x, int y)
	{
		String check;
		for (int i = 0; i < values.length - 1; i++)
		{
			check = values[i];
			if (!quadrantContains(map, check, x, y) && !rowContains(map, check, x, y) && !columnContains(map, check, x, y))
			{
				return check;
			}
		}
		System.err.println("Error: Error in logic #lowestValue#");
		return null;
	}

	/**
	 * 
	 * @param map
	 *            Values for each square
	 * @param values
	 *            List of values
	 * @param possibleValues
	 *            The actual boolean 3d array
	 * @return The updated array
	 */
	public static Boolean[][][] updatePossibilities(String[][] map, String[] values, Boolean[][][] possibleValues)
	{
		String check;
		for (int x = 0; x < map[0].length; x++)
		{
			for (int y = 0; y < map[0].length; y++)
			{

				if (map[x][y].equals(values[values.length - 1]))
				{
					for (int i = 0; i < values.length - 1; i++)
					{
						// System.out.println("Checking at: " + x + " " + y + "
						// : " + map[x][y] + " for: " + values[i]);
						check = values[i];
						if (quadrantContains(map, check, x, y) || rowContains(map, check, x, y) || columnContains(map, check, x, y))
						{
							// System.out.println("Found in quad, row or
							// column");
							possibleValues[x][y][i] = false;
						} else
						{
							// System.out.println("Not found");
							possibleValues[x][y][i] = true;
						}
					}
				} else
				{
					// System.out.println("Locaton filled in at: " + x + " " +
					// y);
					for (int i = 0; i < values.length - 1; i++)
					{
						if (map[x][y].equals(values[i]))
						{
							possibleValues[x][y][i] = true;
						} else
						{
							possibleValues[x][y][i] = false;
						}
					}
				}
			}
		}
		return possibleValues;
	}

	/**
	 * 
	 * @param possibleValues
	 *            The 3d array of the possible values
	 * @param values
	 *            the order
	 * 
	 *            Prints out the possible values
	 */
	public static void printPossibleValues(Boolean[][][] possibleValues, String[] values)
	{
		for (int i = 0; i < possibleValues[0][0].length; i++)
		{
			System.out.println(values[i]);
			for (int y = 0; y < possibleValues[0][0].length; y++)
			{
				for (int x = 0; x < possibleValues[0][0].length; x++)
				{
					if (possibleValues[x][y][i])
					{
						System.out.print("1 ");
					} else
					{
						System.out.print("0 ");
					}
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	/**
	 * 
	 * @param map
	 *            The values of each square Prints out the map
	 */
	public static void PrintMap(String[][] map)
	{
		int quadrantSize = map[0].length;
		for (int y = 0; y < quadrantSize; y++)
		{
			for (int x = 0; x < quadrantSize; x++)
			{
				System.out.print(map[x][y] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * 
	 * @param map
	 * @param values
	 * @return If the grid is filled
	 */
	public static boolean isSolved(String[][] map, String[] values)
	{
		for (int y = 0; y < map[0].length; y++)
		{
			for (int x = 0; x < map[0].length; x++)
			{
				if (map[x][y].equals(values[values.length - 1]))
				{
					return false;
				}
			}
		}
		return true;
	}

	public static boolean sameMap(String[][] input1, String[][] input2)
	{
		for (int x = 0; x < input1[0].length; x++)
		{
			for (int y = 0; y < input1[0].length; y++)
			{
				System.out.println("Checking at: " + x + " " + y + " I1: " + input1[x][y] + " I2: " + input2[x][y]);
				if (!input1[x][y].equals(input2[x][y]))
				{
					System.out.println("Does not match at: " + x + " " + y);
					return false;
				}
			}
		}
		return true;
	}

	public static String[][] copy(String[][] input)
	{
		String[][] temp = new String[input[0].length][input[0].length];
		for (int x = 0; x < input[0].length; x++)
		{
			for (int y = 0; y < input[0].length; y++)
			{
				temp[x][y] =new String( input[x][y]);
			}
		}
		return temp;
	}
}
