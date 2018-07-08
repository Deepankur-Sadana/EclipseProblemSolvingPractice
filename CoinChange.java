
public class CoinChange {

	public static void main(String[] args) {
		changeCoin();
	}

	static void changeCoin() {

		changeCoin(new int[] { 1, 2, 3 }, 4);
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
				dp[i][curSum] = curSum < coins[i] ?
						dp[i - 1][curSum] :
							dp[i - 1][sum] + dp[i][curSum - coins[i]];
			}

		}
		Utils.pint(dp);

	}

}
