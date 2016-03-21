import java.util.ArrayList;

public class Method
{
	public static Boolean[][][]		possibleValues;
	public static ArrayList<String>	moves		= new ArrayList<String>();
	public static boolean			mapChange	= false;

	public static String[][] methodOne(String[][] map, String[] values)
	{
		possibleValues = new Boolean[map[0].length][map[0].length][map[0].length];
		possibleValues = CheckandUtilities.updatePossibilities(map, values, possibleValues);
		String check;
		boolean onlyOne;
		boolean change = true;
		mapChange = false;
		int Xpos;
		int Ypos;
		while (change)
		{
			change = false;
			for (int i = 0; i < map[0].length; i++)
			{
				check = values[i];
				for (int Xquad = 0; Xquad < Math.sqrt(map[0].length); Xquad++)
				{
					for (int Yquad = 0; Yquad < Math.sqrt(map[0].length); Yquad++)
					{
						possibleValues = CheckandUtilities.updatePossibilities(map, values, possibleValues);
						// CheckandUtilities.printPossibleValues(possibleValues);
						// System.out.println("Checking if quadrant");
						// if (!CheckandUtilities.quadrantContains(map, check,
						// (int) Math.sqrt(map[0].length) * Xquad, (int)
						// Math.sqrt(map[0].length) * Yquad))
						// {
						onlyOne = false;
						Xpos = -1;
						Ypos = -1;
						for (int x = 0; x < Math.sqrt(map[0].length); x++)
						{
							for (int y = 0; y < Math.sqrt(map[0].length); y++)
							{
								// System.out.println("Checking in quad: " +
								// Xquad + " " + Yquad + " cord: " + x + " " + y
								// + " for: " + check);
								if (possibleValues[(int) (Math.sqrt(map[0].length) * Xquad + x)][(int) (Math.sqrt(map[0].length) * Yquad + y)][i] && Xpos + Ypos < 0)
								{
									// System.out.println("Only one");
									onlyOne = true;
									Xpos = (int) (Math.sqrt(map[0].length) * Xquad + x);
									Ypos = (int) (Math.sqrt(map[0].length) * Yquad + y);
								} else if (possibleValues[(int) (Math.sqrt(map[0].length) * Xquad + x)][(int) (Math.sqrt(map[0].length) * Yquad + y)][i] && Xpos + Ypos >= 0)
								{
									// System.out.println("Not only one");
									onlyOne = false;
								}
							}
						}
						if (onlyOne && !map[Xpos][Ypos].equals(check))
						{
							moves.add((moves.size() + 1) + ") col:" + (Xpos + 1) + " row:" + (Ypos + 1) + " val:" + check + " by Method One");
							map[Xpos][Ypos] = check;
							change = true;
							mapChange = true;
						}
					}
				}
			}
			// System.out.println();
			// CheckandUtilities.printPossibleValues(possibleValues);
			// CheckandUtilities.PrintMap(map);
		}
		return map;
	}

	public static String[][] methodTwo(String[][] map, String[] values)
	{
		mapChange = false;
		Boolean onlyOne;
		int Xpos;
		int Ypos;
		int Value;
		boolean change = true;
		while (change)
		{
			change = false;
			CheckandUtilities.updatePossibilities(map, values, possibleValues);
			// CheckandUtilities.printPossibleValues(possibleValues, values);
			for (int x = 0; x < values.length - 1; x++)
			{
				for (int y = 0; y < values.length - 1; y++)
				{
					Xpos = -1;
					Ypos = -1;
					Value = -1;
					onlyOne = false;
					for (int i = 0; i < values.length - 1; i++)
					{
						if (possibleValues[x][y][i] && !onlyOne && !map[x][y].equals(values[i]))
						{
							onlyOne = true;
							Xpos = x;
							Ypos = y;
							Value = i;
						} else if (possibleValues[x][y][i] && onlyOne)
						{
							onlyOne = false;
							break;
						}
					}
					if (onlyOne)
					{
						// System.out.println("Only one at: " + Xpos + " " +
						// Ypos + " For: " + values[Value]);
						moves.add((moves.size() + 1) + ") col:" + (Xpos + 1) + " row:" + (Ypos + 1) + " val:" + values[Value] + " by Method Two");
						map[Xpos][Ypos] = values[Value];
						change = true;
						mapChange = true;
					}
				}
			}
		}
		return map;
	}
}
