import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main
{
	/**
	 * 
	 * @param map
	 *            The values within a square
	 * @param values
	 *            A list of the order of the values
	 */
	public static void solve(String[][] map, String[] values)
	{
		String[][] temp;
		int methodNum = 1;
		boolean flag = false;
		while (!CheckandUtilities.isSolved(map, values))
		{
			temp = map.clone();
			switch (methodNum)
			{
			case 1:
				map = Method.methodOne(map, values);
				// System.out.println("m1");
				if (map.equals(temp))
				{
					flag = true;
				}
				methodNum++;
				break;
			case 2:
				map = Method.methodTwo(map, values);
				// System.out.println("m2");
				if (map.equals(temp))
				{
					methodNum++;
				} else
				{
					methodNum = 1;
				}
				break;
			}
			if (flag)
			{
				break;
			}
		}
		CheckandUtilities.PrintMap(map);
		for (String thing : Method.moves)
		{
			System.out.println(thing);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader(new File("Data(" + 3 + ").sdku")));
		int quadrantSize = Integer.parseInt(br.readLine());
		if (Math.pow(Math.sqrt(quadrantSize), 2) != quadrantSize)
		{
			System.err.println("Error: not a square");
			System.exit(0);
		}
		String[] values = br.readLine().split(" ");
		String[][] map = new String[quadrantSize][quadrantSize];
		String[] temp = new String[quadrantSize];
		for (int y = 0; y < quadrantSize; y++)
		{
			temp = br.readLine().split(" ");
			for (int x = 0; x < quadrantSize; x++)
			{
				map[x][y] = temp[x];
			}
		}
		CheckandUtilities.PrintMap(map);
		System.out.println();
		solve(map, values);
		// Graphics.startGraphics(quadrantSize);
		// System.out.println(lowestValue(map, values, 2, 0));
		br.close();
	}

}
