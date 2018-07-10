
public class CoinChange {

	public static void main(String[] args) {
		changeCoin();
	}

	static void changeCoin() {

		climbStairs(new int[] { 1, 2, 3 }, 6);
	}

	static void changeCoin(int[] coins, int sum) {
		int dp[][] = new int[coins.length][sum + 1];

		int firstCoin = coins[0];
		for (int i = 0; i < dp[0].length; i++) {
			dp[0][i] = i < firstCoin ? 1 : dp[0][i - firstCoin];

		}

		for (int i = 1; i < coins.length; i++) {
			for (int curSum = 0; curSum <= sum; curSum++) {
				//
				dp[i][curSum] = curSum < coins[i] ? dp[i - 1][curSum] : dp[i - 1][curSum] + dp[i][curSum - coins[i]];
			}

		}
		Utils.pint(dp);

	}

	static void climbStairs(int[] steps, int height) {
		int dp[][] = new int[steps.length][height + 1];

		for (int i = 0; i < dp[0].length; i++) {
			dp[0][i] = 1;
		}

		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = 1;
		}

		for (int i = 1; i < steps.length; i++) {
			for (int curStep = 1; curStep <= height; curStep++) {
				if (curStep < 2)
					dp[i][curStep] = 1;
				else
					dp[i][curStep] = dp[i][curStep - 1] +  dp[i][curStep - 2];
			}

		}
		Utils.pint(dp);
	}

}