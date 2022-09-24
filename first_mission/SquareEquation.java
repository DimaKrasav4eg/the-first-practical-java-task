package first_mission;

public class SquareEquation {
	/**
	 * Коэффициент <dt>a<dt> квадратного уравнения ax^2 + bx + c = 0
	 */
	private double aRatio;
	/**
	 * Коэффициент <dt>b<dt> квадратного уравнения ax^2 + bx + c = 0
	 */
	private double bRatio;
	/**
	 * Коэффициент <dt>c<dt> квадратного уравнения ax^2 + bx + c = 0
	 */
	private double cRatio;
	
	/**
	 * Конструктор, присваивающий коэффициенты квадратного уравнения 
	 * <dt>ax^2 + bx + c = 0</dt>
	 * @param aRatio
	 * @param bRatio
	 * @param cRatio
	 */
	public SquareEquation(double aRatio, double bRatio, double cRatio) {
		this.aRatio = aRatio;
		this.bRatio = bRatio;
		this.cRatio = cRatio;
		if (this.aRatio != 0) {
			Discriminant.setValue(this.aRatio, this.bRatio, this.cRatio);
		}
	}
	/**
	 * Осуществляет поиск корней квадратного уравнения.
	 * @return roots - корни квадратного уранения (в виде экземпляра 
	 * класса {@link RootsSqEquation}). 
	 * <pre>
	 *нет действительных корней | roots.x1 == NaN
	 *   или уравнение задано   |
	 *       некорректно        | roots.x1 == NaN
	 *
	 *        одинаковые        | roots.x1 == roots.x2
	 *          корни           |
	 *
	 *   корень - любое число   | roots.x1 == POSITIVE_INFINITY
	 *                          | roots.x2 == POSITIVE_INFINITY
	 * </pre>
	 */
	public RootsSqEquation getRoots() {
		RootsSqEquation roots = new RootsSqEquation();
		// линейное уравнение
		if (this.aRatio == 0) {
			// 0x^2 + 0x + c = 0
			if (this.bRatio == 0) {
				// x - любое число
				if (cRatio == 0) {
					roots.nRoots = Roots.INFINITY;
				}
				// уравнение задано некорректно
				else {
					roots.nRoots = Roots.ZERO;
				}
			}
			// совпадающие корни линейного уравнения
			else {
				roots.nRoots = Roots.ONE;
				roots.x1 = -this.cRatio/this.bRatio;
				roots.x2 = -this.cRatio/this.bRatio;
			}
		}
		// два разных действительных корня
		else if (Discriminant.value > 0) {
			roots.nRoots = Roots.TWO;
			roots.x1 = ( Math.sqrt(Discriminant.value) - this.bRatio)/2/this.aRatio;
			roots.x2 = (-Math.sqrt(Discriminant.value) - this.bRatio)/2/this.aRatio;
		}
		// совпадающие корни
		else if (Discriminant.value == 0) {
			roots.nRoots = Roots.ONE;
			roots.x1 = -this.bRatio/2;
			roots.x2 = -this.bRatio/2;
		}
		// действительные корни отсутствуют
		else {
			roots.nRoots = Roots.ZERO;
		}
		return roots;
	}
	/**
	 * Класс, в котором осуществляется подсчет дискриминанта квадратного уравнения
	 */
	private static class Discriminant {
		/**
		 * Значение дискриминанта квадратного уравнения
		 */
		private static double value;
		/**
		 * Осуществляет подсчет дискриминанта, присваивая полученное значение 
		 * переменной {@link Discriminant#value}
		 * @param aRatio
		 * @param bRatio
		 * @param cRatio
		 */
		public static void setValue (double aRatio, double bRatio, double cRatio) {
			value = bRatio*bRatio - 4*aRatio*cRatio;
		}
	}
	/**
	 * Вспомогательный тип для метода {@link SquareEquation#getRoots()}.
	 *
	 */
	public static class RootsSqEquation {
		/**
		 * Количество корней уравнения
		 */
		public Roots nRoots;
		/**
		 * Первый корень уравнения
		 */
		public double x1;
		/**
		 * Второй корень уравнения
		 */
		public double x2;
	}
}
enum Roots {
	ZERO,
	ONE,
	TWO,
	INFINITY
}
