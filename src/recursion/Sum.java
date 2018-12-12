package recursion;

/**
 * 递归
 *
 * @Auther yusiming
 * @Date 2018/11/26 16:15
 */
public class Sum {
    public static int sum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return sum(arr, 0);
    }

    private static int sum(int[] arr, int n) {
        if (n == arr.length) {
            return 0;
        }
        return arr[n] + sum(arr, n + 1);
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7,8};
        int sum = sum(nums);
        System.out.println(sum);
    }
}
