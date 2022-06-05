class TextEditor {
    private int idx = 0;
    private StringBuilder s = new StringBuilder();

    public TextEditor() {

    }

    public void addText(String text) {
        s.insert(idx, text);
        idx += text.length();
    }

    public int deleteText(int k) {
        k = Math.min(idx, k);
        for (int i = 0; i < k; ++i) {
            s.deleteCharAt(--idx);
        }
        return k;
    }

    public String cursorLeft(int k) {
        idx = Math.max(0, idx - k);
        return s.substring(Math.max(0, idx - 10), idx);
    }

    public String cursorRight(int k) {
        idx = Math.min(s.length(), idx + k);
        return s.substring(Math.max(0, idx - 10), idx);
    }
}

/**
 * Your TextEditor object will be instantiated and called as such:
 * TextEditor obj = new TextEditor();
 * obj.addText(text);
 * int param_2 = obj.deleteText(k);
 * String param_3 = obj.cursorLeft(k);
 * String param_4 = obj.cursorRight(k);
 */