public class Range {
    private int left;
    private int right;
    private int size;

    public Range(int left, int right) {
        this.left = left;
        this.right = right;
        this.size = right - left;
    }

    public void setLeft(int left) {
        this.left = left;
        setSize(right - left);
    }

    public void setRight(int right) {
        this.right = right;
        setSize(right - left);
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getSize() {
        return size;
    }

    public String present() {
        StringBuilder sb = new StringBuilder();
        sb.append("[" + left + ", " + right + ") ");
        return sb.toString();
    }
}

