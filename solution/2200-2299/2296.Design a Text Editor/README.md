# [2296. 设计一个文本编辑器](https://leetcode.cn/problems/design-a-text-editor)

[English Version](/solution/2200-2299/2296.Design%20a%20Text%20Editor/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你设计一个带光标的文本编辑器，它可以实现以下功能：</p>

<ul>
	<li><strong>添加：</strong>在光标所在处添加文本。</li>
	<li><strong>删除：</strong>在光标所在处删除文本（模拟键盘的删除键）。</li>
	<li><strong>移动：</strong>将光标往左或者往右移动。</li>
</ul>

<p>当删除文本时，只有光标左边的字符会被删除。光标会留在文本内，也就是说任意时候&nbsp;<code>0 &lt;= cursor.position &lt;= currentText.length</code>&nbsp;都成立。</p>

<p>请你实现&nbsp;<code>TextEditor</code>&nbsp;类：</p>

<ul>
	<li><code>TextEditor()</code>&nbsp;用空文本初始化对象。</li>
	<li><code>void addText(string text)</code>&nbsp;将&nbsp;<code>text</code>&nbsp;添加到光标所在位置。添加完后光标在&nbsp;<code>text</code>&nbsp;的右边。</li>
	<li><code>int deleteText(int k)</code>&nbsp;删除光标左边&nbsp;<code>k</code>&nbsp;个字符。返回实际删除的字符数目。</li>
	<li><code>string cursorLeft(int k)</code> 将光标向左移动&nbsp;<code>k</code>&nbsp;次。返回移动后光标左边&nbsp;<code>min(10, len)</code>&nbsp;个字符，其中&nbsp;<code>len</code>&nbsp;是光标左边的字符数目。</li>
	<li><code>string cursorRight(int k)</code>&nbsp;将光标向右移动&nbsp;<code>k</code>&nbsp;次。返回移动后光标左边&nbsp;<code>min(10, len)</code>&nbsp;个字符，其中&nbsp;<code>len</code>&nbsp;是光标左边的字符数目。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
["TextEditor", "addText", "deleteText", "addText", "cursorRight", "cursorLeft", "deleteText", "cursorLeft", "cursorRight"]
[[], ["leetcode"], [4], ["practice"], [3], [8], [10], [2], [6]]
<strong>输出：</strong>
[null, null, 4, null, "etpractice", "leet", 4, "", "practi"]

<strong>解释：</strong>
TextEditor textEditor = new TextEditor(); // 当前 text 为 "|" 。（'|' 字符表示光标）
textEditor.addText("leetcode"); // 当前文本为 "leetcode|" 。
textEditor.deleteText(4); // 返回 4
                          // 当前文本为 "leet|" 。
                          // 删除了 4 个字符。
textEditor.addText("practice"); // 当前文本为 "leetpractice|" 。
textEditor.cursorRight(3); // 返回 "etpractice"
                           // 当前文本为 "leetpractice|". 
                           // 光标无法移动到文本以外，所以无法移动。
                           // "etpractice" 是光标左边的 10 个字符。
textEditor.cursorLeft(8); // 返回 "leet"
                          // 当前文本为 "leet|practice" 。
                          // "leet" 是光标左边的 min(10, 4) = 4 个字符。
textEditor.deleteText(10); // 返回 4
                           // 当前文本为 "|practice" 。
                           // 只有 4 个字符被删除了。
textEditor.cursorLeft(2); // 返回 ""
                          // 当前文本为 "|practice" 。
                          // 光标无法移动到文本以外，所以无法移动。
                          // "" 是光标左边的 min(10, 0) = 0 个字符。
textEditor.cursorRight(6); // 返回 "practi"
                           // 当前文本为 "practi|ce" 。
                           // "practi" 是光标左边的 min(10, 6) = 6 个字符。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= text.length, k &lt;= 40</code></li>
	<li><code>text</code>&nbsp;只含有小写英文字母。</li>
	<li>调用 <code>addText</code>&nbsp;，<code>deleteText</code>&nbsp;，<code>cursorLeft</code> 和&nbsp;<code>cursorRight</code>&nbsp;的 <strong>总</strong> 次数不超过&nbsp;<code>2 * 10<sup>4</sup></code>&nbsp;次。</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你能设计并实现一个每次调用时间复杂度为 <code>O(k)</code> 的解决方案吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：左右栈**

我们可以使用两个栈 `left` 和 `right`，其中栈 `left` 存储光标左边的字符，另一个栈 `right` 存储光标右边的字符。

-   当调用 `addText` 方法时，我们将 `text` 中的字符依次入栈 `left`。时间复杂度 $O(|text|)$。
-   当调用 `deleteText` 方法时，我们将 `left` 中的字符出栈最多 $k$ 次。时间复杂度 $O(k)$。
-   当调用 `cursorLeft` 方法时，我们将 `left` 中的字符出栈最多 $k$ 次，然后将出栈的字符依次入栈 `right`，最后返回 `left` 栈最多 $10$ 个字符。时间复杂度 $O(k)$。
-   当调用 `cursorRight` 方法时，我们将 `right` 中的字符出栈最多 $k$ 次，然后将出栈的字符依次入栈 `left`，最后返回 `left` 栈最多 $10$ 个字符。时间复杂度 $O(k)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
