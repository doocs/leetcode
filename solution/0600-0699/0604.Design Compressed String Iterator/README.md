# [604. 迭代压缩字符串](https://leetcode.cn/problems/design-compressed-string-iterator)

[English Version](/solution/0600-0699/0604.Design%20Compressed%20String%20Iterator/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计并实现一个迭代压缩字符串的数据结构。给定的压缩字符串的形式是，每个字母后面紧跟一个正整数，表示该字母在原始未压缩字符串中出现的次数。</p>

<p>设计一个数据结构，它支持如下两种操作：&nbsp;<code>next</code>&nbsp;和&nbsp;<code>hasNext</code>。</p>

<ul>
	<li><code>next()</code> - 如果原始字符串中仍有未压缩字符，则返回<strong>下一个字符</strong>，否则返回<strong>空格</strong>。</li>
	<li><code>hasNext()</code> - 如果原始字符串中存在未压缩的的字母，则返回true，否则返回<code>false</code>。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
["StringIterator", "next", "next", "next", "next", "next", "next", "hasNext", "next", "hasNext"]
[["L1e2t1C1o1d1e1"], [], [], [], [], [], [], [], [], []]
<b>输出：</b>
[null, "L", "e", "e", "t", "C", "o", true, "d", true]

<strong>解释：</strong>
StringIterator stringIterator = new StringIterator("L1e2t1C1o1d1e1");
stringIterator.next(); // 返回 "L"
stringIterator.next(); // 返回 "e"
stringIterator.next(); // 返回 "e"
stringIterator.next(); // 返回 "t"
stringIterator.next(); // 返回 "C"
stringIterator.next(); // 返回 "o"
stringIterator.hasNext(); // 返回 True
stringIterator.next(); // 返回 "d"
stringIterator.hasNext(); // 返回 True</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;compressedString.length &lt;= 1000</code></li>
	<li><code>compressedString</code>&nbsp;由小写字母、大写字母和数字组成。</li>
	<li>在&nbsp;<code>compressedString</code>&nbsp;中，单个字符的重复次数在&nbsp;<code>[1,10 ^9]</code>&nbsp;范围内。</li>
	<li><code>next</code>&nbsp;和&nbsp;<code>hasNext</code>&nbsp;的操作数最多为&nbsp;<code>100</code>&nbsp;。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：解析存储**

将 `compressedString` 解析成字符 $c$ 和对应的重复次数 $x$，存储在数组或列表 $d$ 中，用 $p$ 指向当前字符。

然后在 `next` 和 `hasNext` 中进行操作。

初始化的时间复杂度为 $O(n)$，其余操作的时间复杂度为 $O(1)$。其中 $n$ 为 `compressedString` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class StringIterator:

    def __init__(self, compressedString: str):
        self.d = []
        self.p = 0
        n = len(compressedString)
        i = 0
        while i < n:
            c = compressedString[i]
            x = 0
            i += 1
            while i < n and compressedString[i].isdigit():
                x = x * 10 + int(compressedString[i])
                i += 1
            self.d.append([c, x])

    def next(self) -> str:
        if not self.hasNext():
            return ' '
        ans = self.d[self.p][0]
        self.d[self.p][1] -= 1
        if self.d[self.p][1] == 0:
            self.p += 1
        return ans

    def hasNext(self) -> bool:
        return self.p < len(self.d) and self.d[self.p][1] > 0


# Your StringIterator object will be instantiated and called as such:
# obj = StringIterator(compressedString)
# param_1 = obj.next()
# param_2 = obj.hasNext()
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class StringIterator {
    private List<Node> d = new ArrayList<>();
    private int p;

    public StringIterator(String compressedString) {
        int n = compressedString.length();
        int i = 0;
        while (i < n) {
            char c = compressedString.charAt(i);
            int x = 0;
            while (++i < n && Character.isDigit(compressedString.charAt(i))) {
                x = x * 10 + (compressedString.charAt(i) - '0');
            }
            d.add(new Node(c, x));
        }
    }

    public char next() {
        if (!hasNext()) {
            return ' ';
        }
        char ans = d.get(p).c;
        if (--d.get(p).x == 0) {
            ++p;
        }
        return ans;
    }

    public boolean hasNext() {
        return p < d.size() && d.get(p).x > 0;
    }
}

class Node {
    char c;
    int x;

    Node(char c, int x) {
        this.c = c;
        this.x = x;
    }
}

/**
 * Your StringIterator object will be instantiated and called as such:
 * StringIterator obj = new StringIterator(compressedString);
 * char param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
```

### **C++**

```cpp
class StringIterator {
public:
    StringIterator(string compressedString) {
        int n = compressedString.size();
        int i = 0;
        while (i < n) {
            char c = compressedString[i];
            int x = 0;
            while (++i < n && isdigit(compressedString[i])) {
                x = x * 10 + (compressedString[i] - '0');
            }
            d.push_back({c, x});
        }
    }

    char next() {
        if (!hasNext()) return ' ';
        char ans = d[p].first;
        if (--d[p].second == 0) {
            ++p;
        }
        return ans;
    }

    bool hasNext() {
        return p < d.size() && d[p].second > 0;
    }
private:
    vector<pair<char, int>> d;
    int p = 0;
};

/**
 * Your StringIterator object will be instantiated and called as such:
 * StringIterator* obj = new StringIterator(compressedString);
 * char param_1 = obj->next();
 * bool param_2 = obj->hasNext();
 */
```

### **Go**

```go
type pair struct {
	c byte
	x int
}

type StringIterator struct {
	d []pair
	p int
}

func Constructor(compressedString string) StringIterator {
	n := len(compressedString)
	i := 0
	d := []pair{}
	for i < n {
		c := compressedString[i]
		x := 0
		i++
		for i < n && compressedString[i] >= '0' && compressedString[i] <= '9' {
			x = x*10 + int(compressedString[i]-'0')
			i++
		}
		d = append(d, pair{c, x})
	}
	return StringIterator{d, 0}
}

func (this *StringIterator) Next() byte {
	if !this.HasNext() {
		return ' '
	}
	ans := this.d[this.p].c
	this.d[this.p].x--
	if this.d[this.p].x == 0 {
		this.p++
	}
	return ans
}

func (this *StringIterator) HasNext() bool {
	return this.p < len(this.d) && this.d[this.p].x > 0
}

/**
 * Your StringIterator object will be instantiated and called as such:
 * obj := Constructor(compressedString);
 * param_1 := obj.Next();
 * param_2 := obj.HasNext();
 */
```

### **...**

```

```

<!-- tabs:end -->
