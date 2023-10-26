import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		double totalTimeAscending = 0.0;
		double totalTimeDescending = 0.0;
		double totalTimeRandom = 0.0;

		double[] timesAscending = new double[10];
		double[] timesDescending = new double[10];
		double[] timesRandom = new double[10];

		for (int i = 0; i < 10; i++) {
				int[] ascendingArray = generateAscendingArray(100000);
				int[] descendingArray = generateDescendingArray(100000);
				int[] randomArray = generateRandomArray(100000);

				System.out.println("--------------COMEÇOU--------------");

				long startTime = System.nanoTime();
				mergeSort(ascendingArray);
				long endTime = System.nanoTime();
				double elapsedTime = (endTime - startTime) / 1e9;
				System.out.printf("Tempo de execução com Array crescente: %.4f segundos%n", elapsedTime);
				totalTimeAscending += elapsedTime;
				timesAscending[i] = elapsedTime;

				startTime = System.nanoTime();
				mergeSort(descendingArray);
				endTime = System.nanoTime();
				elapsedTime = (endTime - startTime) / 1e9;
				System.out.printf("Tempo de execução com Array decrescente: %.4f segundos%n", elapsedTime);
				totalTimeDescending += elapsedTime;
				timesDescending[i] = elapsedTime;

				startTime = System.nanoTime();
				mergeSort(randomArray);
				endTime = System.nanoTime();
				elapsedTime = (endTime - startTime) / 1e9;
				System.out.printf("Tempo de execução com Array aleatório: %.4f segundos%n", elapsedTime);
				totalTimeRandom += elapsedTime;
				timesRandom[i] = elapsedTime;

				System.out.println("--------------ACABOU--------------\n\n\n\n\n");
		}

			double averageTimeAscending = totalTimeAscending / 10;
			double averageTimeDescending = totalTimeDescending / 10;
			double averageTimeRandom = totalTimeRandom / 10;

			System.out.println("Média de tempo de execução com Array crescente: " + averageTimeAscending + " segundos");
			System.out.println("Média de tempo de execução com Array decrescente: " + averageTimeDescending + " segundos");
			System.out.println("Média de tempo de execução com Array aleatório: " + averageTimeRandom + " segundos");

			double standardDeviationAscending = calculateStandardDeviation(timesAscending, averageTimeAscending);
			double standardDeviationDescending = calculateStandardDeviation(timesDescending, averageTimeDescending);
			double standardDeviationRandom = calculateStandardDeviation(timesRandom, averageTimeRandom);

			System.out.println("Desvio padrão do tempo de execução com Array crescente: " + standardDeviationAscending + " segundos");
			System.out.println("Desvio padrão do tempo de execução com Array decrescente: " + standardDeviationDescending + " segundos");
			System.out.println("Desvio padrão do tempo de execução com Array aleatório: " + standardDeviationRandom + " segundos");
		}

		public static double calculateStandardDeviation(double[] times, double average) {
				double variance = calculateVariance(times, average);
				return Math.sqrt(variance);
		}

		public static double calculateVariance(double[] times, double average) {
				double sumOfSquaredDifferences = 0.0;
				for (double time : times) {
						double difference = time - average;
						sumOfSquaredDifferences += difference * difference;
				}
				return sumOfSquaredDifferences / times.length;
		}

    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        int middle = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }

    public static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < left.length) {
            arr[k] = left[i];
            i++;
            k++;
        }

        while (j < right.length) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }

    public static int[] generateAscendingArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    public static int[] generateDescendingArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i;
        }
        return array;
    }

    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * size) + 1;
        }
        return array;
    }
}
