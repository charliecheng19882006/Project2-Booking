public class test {
    public static void main(String args[]) {
        RangeList ri = new RangeList();
        ri.add(new int[] {1, 5});
        ri.add(new int[] {10, 20});
        ri.add(new int[] {20, 20});
        ri.add(new int[] {20, 21});
        ri.add(new int[] {2, 4});
        ri.add(new int[] {3, 8});
        ri.remove(new int[] {10, 10});
        ri.remove(new int[] {10, 11});
        ri.remove(new int[] {15, 17});
        ri.remove(new int[] {3, 19});
        ri.print();
    }
}
