import java.util.Arrays;

public class QuickSort {

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
				quickSort(ascendingArray, 0, ascendingArray.length - 1);
				long endTime = System.nanoTime();
				double elapsedTime = (endTime - startTime) / 1e9;
				System.out.printf("Tempo de execução com Array crescente: %.4f segundos%n", elapsedTime);
				totalTimeAscending += elapsedTime;
				timesAscending[i] = elapsedTime;

				startTime = System.nanoTime();
				quickSort(descendingArray, 0, descendingArray.length - 1);
				endTime = System.nanoTime();
				elapsedTime = (endTime - startTime) / 1e9;
				System.out.printf("Tempo de execução com Array decrescente: %.4f segundos%n", elapsedTime);
				totalTimeDescending += elapsedTime;
				timesDescending[i] = elapsedTime;

				startTime = System.nanoTime();
				quickSort(randomArray, 0, randomArray.length - 1);
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

			double varianceAscending = calculateVariance(timesAscending, averageTimeAscending);
			double standardDeviationAscending = Math.sqrt(varianceAscending);

			double varianceDescending = calculateVariance(timesDescending, averageTimeDescending);
			double standardDeviationDescending = Math.sqrt(varianceDescending);

			double varianceRandom = calculateVariance(timesRandom, averageTimeRandom);
			double standardDeviationRandom = Math.sqrt(varianceRandom);

			System.out.println("Desvio padrão do tempo de execução com Array crescente: " + standardDeviationAscending + " segundos");
			System.out.println("Desvio padrão do tempo de execução com Array decrescente: " + standardDeviationDescending + " segundos");
			System.out.println("Desvio padrão do tempo de execução com Array aleatório: " + standardDeviationRandom + " segundos");
		}

		public static double calculateVariance(double[] times, double average) {
				double sumOfSquaredDifferences = 0.0;
				for (double time : times) {
						double difference = time - average;
						sumOfSquaredDifferences += difference * difference;
				}
				return sumOfSquaredDifferences / times.length;
		}

    public static void quickSort(int[] arr, int low, int high) {
        while (low < high) {
            int pivotIndex = partition(arr, low, high);
            if (pivotIndex - low < high - pivotIndex) {
                quickSort(arr, low, pivotIndex - 1);
                low = pivotIndex + 1;
            } else {
                quickSort(arr, pivotIndex + 1, high);
                high = pivotIndex - 1;
            }
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low;
        for (int j = low + 1; j <= high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i];
        arr[i] = arr[low];
        arr[low] = temp;
        return i;
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
