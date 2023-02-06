# [2296. Design a Text Editor](https://leetcode.com/problems/design-a-text-editor)

[中文文档](/solution/2200-2299/2296.Design%20a%20Text%20Editor/README.md)

## Description

<p>Design a text editor with a cursor that can do the following:</p>

<ul>
	<li><strong>Add</strong> text to where the cursor is.</li>
	<li><strong>Delete</strong> text from where the cursor is (simulating the backspace key).</li>
	<li><strong>Move</strong> the cursor either left or right.</li>
</ul>

<p>When deleting text, only characters to the left of the cursor will be deleted. The cursor will also remain within the actual text and cannot be moved beyond it. More formally, we have that <code>0 &lt;= cursor.position &lt;= currentText.length</code> always holds.</p>

<p>Implement the <code>TextEditor</code> class:</p>

<ul>
	<li><code>TextEditor()</code> Initializes the object with empty text.</li>
	<li><code>void addText(string text)</code> Appends <code>text</code> to where the cursor is. The cursor ends to the right of <code>text</code>.</li>
	<li><code>int deleteText(int k)</code> Deletes <code>k</code> characters to the left of the cursor. Returns the number of characters actually deleted.</li>
	<li><code>string cursorLeft(int k)</code> Moves the cursor to the left <code>k</code> times. Returns the last <code>min(10, len)</code> characters to the left of the cursor, where <code>len</code> is the number of characters to the left of the cursor.</li>
	<li><code>string cursorRight(int k)</code> Moves the cursor to the right <code>k</code> times. Returns the last <code>min(10, len)</code> characters to the left of the cursor, where <code>len</code> is the number of characters to the left of the cursor.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;TextEditor&quot;, &quot;addText&quot;, &quot;deleteText&quot;, &quot;addText&quot;, &quot;cursorRight&quot;, &quot;cursorLeft&quot;, &quot;deleteText&quot;, &quot;cursorLeft&quot;, &quot;cursorRight&quot;]
[[], [&quot;leetcode&quot;], [4], [&quot;practice&quot;], [3], [8], [10], [2], [6]]
<strong>Output</strong>
[null, null, 4, null, &quot;etpractice&quot;, &quot;leet&quot;, 4, &quot;&quot;, &quot;practi&quot;]

<strong>Explanation</strong>
TextEditor textEditor = new TextEditor(); // The current text is &quot;|&quot;. (The &#39;|&#39; character represents the cursor)
textEditor.addText(&quot;leetcode&quot;); // The current text is &quot;leetcode|&quot;.
textEditor.deleteText(4); // return 4
                          // The current text is &quot;leet|&quot;. 
                          // 4 characters were deleted.
textEditor.addText(&quot;practice&quot;); // The current text is &quot;leetpractice|&quot;. 
textEditor.cursorRight(3); // return &quot;etpractice&quot;
                           // The current text is &quot;leetpractice|&quot;. 
                           // The cursor cannot be moved beyond the actual text and thus did not move.
                           // &quot;etpractice&quot; is the last 10 characters to the left of the cursor.
textEditor.cursorLeft(8); // return &quot;leet&quot;
                          // The current text is &quot;leet|practice&quot;.
                          // &quot;leet&quot; is the last min(10, 4) = 4 characters to the left of the cursor.
textEditor.deleteText(10); // return 4
                           // The current text is &quot;|practice&quot;.
                           // Only 4 characters were deleted.
textEditor.cursorLeft(2); // return &quot;&quot;
                          // The current text is &quot;|practice&quot;.
                          // The cursor cannot be moved beyond the actual text and thus did not move. 
                          // &quot;&quot; is the last min(10, 0) = 0 characters to the left of the cursor.
textEditor.cursorRight(6); // return &quot;practi&quot;
                           // The current text is &quot;practi|ce&quot;.
                           // &quot;practi&quot; is the last min(10, 6) = 6 characters to the left of the cursor.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= text.length, k &lt;= 40</code></li>
	<li><code>text</code> consists of lowercase English letters.</li>
	<li>At most <code>2 * 10<sup>4</sup></code> calls <strong>in total</strong> will be made to <code>addText</code>, <code>deleteText</code>, <code>cursorLeft</code> and <code>cursorRight</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow-up:</strong> Could you find a solution with time complexity of <code>O(k)</code> per call?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class TextEditor:

    def __init__(self):
        self.left = []
        self.right = []

    def addText(self, text: str) -> None:
        self.left.extend(list(text))

    def deleteText(self, k: int) -> int:
        k = min(k, len(self.left))
        for _ in range(k):
            self.left.pop()
        return k

    def cursorLeft(self, k: int) -> str:
        k = min(k, len(self.left))
        for _ in range(k):
            self.right.append(self.left.pop())
        return ''.join(self.left[-10:])

    def cursorRight(self, k: int) -> str:
        k = min(k, len(self.right))
        for _ in range(k):
            self.left.append(self.right.pop())
        return ''.join(self.left[-10:])

# Your TextEditor object will be instantiated and called as such:
# obj = TextEditor()
# obj.addText(text)
# param_2 = obj.deleteText(k)
# param_3 = obj.cursorLeft(k)
# param_4 = obj.cursorRight(k)
```

### **Java**

```java
class TextEditor {
    private StringBuilder left = new StringBuilder();
    private StringBuilder right = new StringBuilder();

    public TextEditor() {
    }

    public void addText(String text) {
        left.append(text);
    }

    public int deleteText(int k) {
        k = Math.min(k, left.length());
        left.setLength(left.length() - k);
        return k;
    }

    public String cursorLeft(int k) {
        k = Math.min(k, left.length());
        for (int i = 0; i < k; ++i) {
            right.append(left.charAt(left.length() - 1));
            left.deleteCharAt(left.length() - 1);
        }
        return left.substring(Math.max(left.length() - 10, 0));
    }

    public String cursorRight(int k) {
        k = Math.min(k, right.length());
        for (int i = 0; i < k; ++i) {
            left.append(right.charAt(right.length() - 1));
            right.deleteCharAt(right.length() - 1);
        }
        return left.substring(Math.max(left.length() - 10, 0));
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
```

### **C++**

```cpp
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
```

### **Go**

```go
type TextEditor struct {
	left, right []byte
}

func Constructor() TextEditor {
	return TextEditor{}
}

func (this *TextEditor) AddText(text string) {
	this.left = append(this.left, text...)
}

func (this *TextEditor) DeleteText(k int) int {
	k = min(k, len(this.left))
	if k < len(this.left) {
		this.left = this.left[:len(this.left)-k]
	} else {
		this.left = []byte{}
	}
	return k
}

func (this *TextEditor) CursorLeft(k int) string {
	k = min(k, len(this.left))
	for ; k > 0; k-- {
		this.right = append(this.right, this.left[len(this.left)-1])
		this.left = this.left[:len(this.left)-1]
	}
	return string(this.left[max(len(this.left)-10, 0):])
}

func (this *TextEditor) CursorRight(k int) string {
	k = min(k, len(this.right))
	for ; k > 0; k-- {
		this.left = append(this.left, this.right[len(this.right)-1])
		this.right = this.right[:len(this.right)-1]
	}
	return string(this.left[max(len(this.left)-10, 0):])
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

/**
 * Your TextEditor object will be instantiated and called as such:
 * obj := Constructor();
 * obj.AddText(text);
 * param_2 := obj.DeleteText(k);
 * param_3 := obj.CursorLeft(k);
 * param_4 := obj.CursorRight(k);
 */
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
