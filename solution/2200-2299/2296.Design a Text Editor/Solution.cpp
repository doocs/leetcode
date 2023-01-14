class TextEditor {
public:
    TextEditor() {
    }

    void addText(string text) {
        left += text;
    }

    int deleteText(int k) {
        k = min(k, (int) left.size());
        left.resize(left.size() - k);
        return k;
    }

    string cursorLeft(int k) {
        k = min(k, (int) left.size());
        while (k--) {
            right += left.back();
            left.pop_back();
        }
        return left.substr(max(0, (int) left.size() - 10));
    }

    string cursorRight(int k) {
        k = min(k, (int) right.size());
        while (k--) {
            left += right.back();
            right.pop_back();
        }
        return left.substr(max(0, (int) left.size() - 10));
    }

private:
    string left, right;
};

/**
 * Your TextEditor object will be instantiated and called as such:
 * TextEditor* obj = new TextEditor();
 * obj->addText(text);
 * int param_2 = obj->deleteText(k);
 * string param_3 = obj->cursorLeft(k);
 * string param_4 = obj->cursorRight(k);
 */