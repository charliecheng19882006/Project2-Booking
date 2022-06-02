import java.util.LinkedList;
import java.util.List;

public class RangeList {
    private List<Range> list = new LinkedList<>();
    public void add(int[] array) {
        if (array.length != 2) {
            return;
        }
        if (array[0] >= array[1]) {
            return;
        }
        if (list.size() == 0) {
            list.add(new Range(array[0], array[1]));
            return;
        }
        if (array[0] > list.get(list.size() - 1).getRight()) {
            list.add(new Range(array[0], array[1]));
            return;
        }
        if (array[0] == list.get(list.size() - 1).getRight()) {
            list.get(list.size() - 1).setRight(array[1]);
            return;
        }
        if (array[1] < list.get(0).getLeft()) {
            list.add(0, new Range(array[0], array[1]));
            return;
        }
        if (array[1] == list.get(0).getLeft()) {
            list.get(0).setLeft(array[0]);
            return;
        }
        int start = findStart(array);
        int end = -1;
        List<Range> newList = new LinkedList<>();
        if (start != -1) {
            addRangeBeforeStart(start, list, newList);
            end = findEnd(array, start);
            if (end != -1) {
                newList.add(new Range(list.get(start).getLeft(), list.get(end).getRight()));
            } else {
                end = findNewEnd(array, start);
                newList.add(new Range(list.get(start). getLeft(), array[1]));
            }
        } else {
            start = findNewStart(array);
            addRangeBeforeStart(start, list, newList);
            end = findEnd(array, start);
            if (end != -1) {
                newList.add(new Range(array[0], list.get(end).getRight()));
            } else {
                end = findNewEnd(array, start);
                newList.add(new Range(array[0], array[1]));
            }
        }
        addRangeAfterEnd(end, list, newList);
        list = newList;
        return;
    }

    public void remove(int[] array) {
        if (array.length != 2) {
            return;
        }
        if (array[0] >= array[1]) {
            return;
        }
        if (array[0] >= list.get(list.size() - 1).getRight()) {
            return;
        }
        if (array[1] <= list.get(0).getLeft()) {
            return;
        }
        int start = findStartR(array);
        int end = -1;
        List<Range> newList = new LinkedList<>();
        if (start != -1) {
            addRangeBeforeStart(start, list, newList);
            end = findEndR(array, start);
            if (end != -1) {
                if (array[0] > list.get(start).getLeft()) {
                    newList.add(new Range(list.get(start).getLeft(), array[0]));
                }
                if (array[1] < list.get(end).getRight()) {
                    newList.add(new Range(array[1], list.get(end).getRight()));
                }
            } else {
                end = findNewEndR(array, start);
                if (array[0] > list.get(start).getLeft()) {
                    newList.add(new Range(list.get(start).getLeft(), array[0]));
                }
            }
        } else {
            start = findNewStartR(array);
            addRangeBeforeStart(start, list, newList);
            end = findEndR(array, start);
            if (end != -1) {
                if (array[1] < list.get(end).getRight()) {
                    newList.add(new Range(array[1], list.get(end).getRight()));
                }
            } else {
                end = findNewEndR(array, start);
            }
        }
        addRangeAfterEnd(end, list, newList);
        list = newList;
        return;
    }

    public void print() {
        for (Range range : list) {
            System.out.print(range.present());
        }
    }

    private int findNewStartR(int[] array) {
        int newStart = -1;
        if (array[0] < list.get(0).getLeft()) {
            newStart = 0;
        } else {
            for (int i = 0; i < list.size() - 1; i++) {
                if (array[0] >= list.get(i).getRight() && array[0] < list.get(i + 1).getLeft()) {
                    newStart = i + 1;
                    break;
                }
            }
        }
        return newStart;
    }

    private int findNewEndR(int[] array, int start) {
        int newEnd = -1;
        if (array[1] > list.get(list.size() - 1).getRight()) {
            newEnd = list.size()- 1;
        } else {
            for (int i = start; i < list.size() - 1; i++) {
                if (array[1] > list.get(i).getRight() && array[1] <= list.get(i + 1).getLeft()) {
                    newEnd = i;
                    break;
                }
            }
        }
        return newEnd;
    }

    private int findStartR(int[] array) {
        int start = -1;
        for (int i = 0; i <list.size(); i++) {
            if (array[0] >= list.get(i).getLeft() && array[0] < list.get(i).getRight()) {
                start = i;
                break;
            }
        }
        return start;
    }

    private int findEndR(int[] array, int start) {
        int end = -1;
        for (int i = start; i < list.size(); i++) {
            if (array[1] > list.get(i).getLeft() && array[1] <= list.get(i).getRight()) {
                end = i;
                break;
            }
        }
        return end;
    }

    private void addRangeBeforeStart(int start, List<Range> list, List<Range> newList) {
        for (int i  = 0; i < start; i++) {
            newList.add(list.get(i));
        }
    }

    private void addRangeAfterEnd(int end, List<Range> list, List<Range> newList) {
        for (int i = end + 1; i < list.size(); i++) {
            newList.add(list.get(i));
        }
    }

    private int findStart(int[] array) {
        int start = -1;
        for (int i = 0; i <list.size(); i++) {
            if (array[0] >= list.get(i).getLeft() && array[0] <= list.get(i).getRight()) {
                start = i;
                break;
            }
        }
        return start;
    }

    private int findEnd(int[] array, int start) {
        int end = -1;
        for (int i = start; i < list.size(); i++) {
            if (array[1] >= list.get(i).getLeft() && array[1] <= list.get(i).getRight()) {
                end = i;
                break;
            }
        }
        return end;
    }

    private int findNewEnd(int[] array, int start) {
        int newEnd = -1;
        if (array[1] > list.get(list.size() - 1).getRight()) {
            newEnd = list.size()- 1;
        } else {
            for (int i = start; i < list.size() - 1; i++) {
                if (array[1] > list.get(i).getRight() && array[1] < list.get(i + 1).getLeft()) {
                    newEnd = i;
                    break;
                }
            }
        }
        return newEnd;
    }

    private int findNewStart(int[] array) {
        int newStart = -1;
        if (array[0] < list.get(0).getLeft()) {
            newStart = 0;
        } else {
            for (int i = 0; i < list.size() - 1; i++) {
                if (array[0] > list.get(i).getRight() && array[0] < list.get(i + 1).getLeft()) {
                    newStart = i + 1;
                    break;
                }
            }
        }
        return newStart;
    }
}
