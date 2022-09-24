package second_mission;

import java.util.Scanner;

public class DiceGame {
	
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.print("Input a quantity of players: ");
		int N = in.nextInt();
		System.out.print("Input a quantity of dice: ");
		int K = in.nextInt();
		Game myGame = new Game();
		myGame.startGame(N, K);
			
	}
	/**
	 * Класс содержащий весь "функционал" "игры"
	 */
	public static class Game {
		/**
		 * Запускает один раунд игры в кости
		 * @param nPlayers число игроков в раунде
		 * @param nDice    число кубиков в раунде
		 * @param startPlayer игрок, с которого начинат кидать кубики
		 * @return winner номер победившего игрока
		 */
		private int startRound (int nPlayers, int nDice, int startPlayer) {
			/**
			 * номер игрока, победившего в раунде
			 */
			int winner    = 0;
			/**
			 * сумма, выпавшая на костях
			 */
			int sumValues = 0;
			/**
			 * максимальная сумма, выпавшая на костях
			 */
			int maxSum    = 0;
			/**
			 * игрок, бросащий кости в данный момент
			 */
			int player    = startPlayer;
			/**
			 * Число, выпавшее на одном кубике
			 */
			int currentValue;
			// возможно лучше создать отдельный класс для броска самих кубиков?
			do {
				System.out.printf("The dice are thrown by the %d player:", player%nPlayers);
				// кидаются кубики
				for (int dice = 0; dice < nDice; ++dice) {
					
					currentValue = (int) Math.round(Math.random()*6);// мало, могу перевести из long в int
					System.out.print(" " + currentValue);
					sumValues += currentValue;
				}
				System.out.printf("\nSum of points: %d\n", sumValues);
				if (maxSum < sumValues) {
					maxSum = sumValues;
					winner = player;
				}
				sumValues = 0;
				++player;
			}
			while (player%nPlayers != startPlayer); 
			System.out.printf("Player %d won!\n", winner);
			return winner;
		}
		/**
		 * Запускает игру в кости
		 * @param nPlayers число игроков
		 * @param nDice число кубиков
		 */
		public void startGame (int nPlayers, int nDice) {
			/**
			 * номер игрока, с которого начинается раунд
			 */
			int startPlayer = 0;
			/**
			 * флаг начала раунда
			 */
			int nextRound = 0;
			// совсем не понимаю почему иногда сваливается в бесконечный цикл и перестает спрашивать ответ
			// со считыванием строки пока не вышло(
			do {
				startPlayer = startRound(nPlayers, nDice, startPlayer);
				System.out.println("Next round? Write 1/0: ");
				nextRound = in.nextInt();
			}while(nextRound == 1);
		}
	}

}