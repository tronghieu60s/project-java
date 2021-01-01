package Helpers;

public class Matrix {

    public void createMatrix(int m, int n, int[][] arr) {
        // Number Of Image = 50
        int images = Config.numOfImage;
        int N = m * n;
        int b[] = new int[m * n + images];
        int c[] = new int[m * n + images];
        for (int i = 0; i < images; i++) {
            b[i] = i;
            c[i] = (int) (Math.random() * 1000000);
        }
        for (int i = 0; i < images - 1; i++) {
            for (int j = i + 1; j < images; j++) {
                if (c[i] > c[j]) {
                    int tmp = b[i];
                    b[i] = b[j];
                    b[j] = tmp;
                    tmp = c[i];
                    c[i] = c[j];
                    c[j] = tmp;
                }
            }
        }
        for (int i = N / 2; i < N; i++) {
            b[i] = b[i - N / 2];
        }
        for (int i = 0; i < m * n; i++) {
            c[i] = (int) (Math.random() * 1000000);
        }
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (c[i] > c[j]) {
                    int tmp = b[i];
                    b[i] = b[j];
                    b[j] = tmp;
                    tmp = c[i];
                    c[i] = c[j];
                    c[j] = tmp;
                }
            }
        }
        N = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = b[N++];
            }
        }
    }

    public void showMatrix(int x, int y, int[][] arr) {
        System.out.println("-----------------------");
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.printf("%3d", arr[i][j]);
            }
            System.out.println();
        }
        System.out.println("-----------------------");
        System.out.println();
    }

}
