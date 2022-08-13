# [1286. 字母组合迭代器](https://leetcode.cn/problems/iterator-for-combination)

[English Version](/solution/1200-1299/1286.Iterator%20for%20Combination/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你设计一个迭代器类&nbsp;<code>CombinationIterator</code>&nbsp;，包括以下内容：</p>

<ul>
	<li><code>CombinationIterator(string characters, int combinationLength)</code>&nbsp;一个构造函数，输入参数包括：用一个&nbsp;<strong>有序且字符唯一&nbsp;</strong>的字符串&nbsp;<code>characters</code>（该字符串只包含小写英文字母）和一个数字&nbsp;<code>combinationLength</code>&nbsp;。</li>
	<li>函数&nbsp;<em><code>next()</code>&nbsp;</em>，按&nbsp;<strong>字典序&nbsp;</strong>返回长度为&nbsp;<code>combinationLength</code> 的下一个字母组合。</li>
	<li>函数&nbsp;<em><code>hasNext()</code>&nbsp;</em>，只有存在长度为&nbsp;<code>combinationLength</code> 的下一个字母组合时，才返回&nbsp;<code>true</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong>
["CombinationIterator", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
[["abc", 2], [], [], [], [], [], []]
<strong>输出：</strong>
[null, "ab", true, "ac", true, "bc", false]
<strong>解释：
</strong>CombinationIterator iterator = new CombinationIterator("abc", 2); // 创建迭代器 iterator
iterator.next(); // 返回 "ab"
iterator.hasNext(); // 返回 true
iterator.next(); // 返回 "ac"
iterator.hasNext(); // 返回 true
iterator.next(); // 返回 "bc"
iterator.hasNext(); // 返回 false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= combinationLength &lt;=&nbsp;characters.length &lt;= 15</code></li>
	<li>&nbsp;<code>characters</code>&nbsp;中每个字符都 <strong>不同</strong></li>
	<li>每组测试数据最多对&nbsp;<code>next</code>&nbsp;和&nbsp;<code>hasNext</code>&nbsp;调用&nbsp;<code>10<sup>4</sup></code>次</li>
	<li>题目保证每次调用函数&nbsp;<code>next</code>&nbsp;时都存在下一个字母组合。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS 回溯**

我们通过 $DFS$ 枚举，预处理生成所有长度为 $combinationLength$ 的字符串，存放到 $cs$ 数组中。

**方法二：二进制编码**

我们看个例子，对于 $abcd$，若 $combinationLength$ 为 2，则 $cs$ 就是 $ab, ac, ad, bc, bd, cd, ...$。

对应的二进制数为：

```
1100
1010
1001
0110
0101
0011
...
```

观察到上述规律后，我们依次按照二进制编码从大到小的规律，将所有字符串依次求出。

所谓的长度 $combinationLength$，只需要满足二进制编码中 $1$ 的个数满足要求即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class CombinationIterator:
    def __init__(self, characters: str, combinationLength: int):
        def dfs(i):
            if len(t) == combinationLength:
                cs.append(''.join(t))
                return
            if i == n:
                return
            t.append(characters[i])
            dfs(i + 1)
            t.pop()
            dfs(i + 1)

        cs = []
        n = len(characters)
        t = []
        dfs(0)
        self.cs = cs
        self.idx = 0

    def next(self) -> str:
        ans = self.cs[self.idx]
        self.idx += 1
        return ans

    def hasNext(self) -> bool:
        return self.idx < len(self.cs)


# Your CombinationIterator object will be instantiated and called as such:
# obj = CombinationIterator(characters, combinationLength)
# param_1 = obj.next()
# param_2 = obj.hasNext()
```

```python
class CombinationIterator:

    def __init__(self, characters: str, combinationLength: int):
        self.curr = (1 << len(characters)) - 1
        self.size = combinationLength
        self.cs = characters[::-1]

    def next(self) -> str:
        while self.curr >= 0 and self.curr.bit_count() != self.size:
            self.curr -= 1
        ans = []
        for i in range(len(self.cs)):
            if (self.curr >> i) & 1:
                ans.append(self.cs[i])
        self.curr -= 1
        return ''.join(ans[::-1])

    def hasNext(self) -> bool:
        while self.curr >= 0 and self.curr.bit_count() != self.size:
            self.curr -= 1
        return self.curr >= 0


# Your CombinationIterator object will be instantiated and called as such:
# obj = CombinationIterator(characters, combinationLength)
# param_1 = obj.next()
# param_2 = obj.hasNext()
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class CombinationIterator {
    private int n;
    private int combinationLength;
    private String characters;
    private StringBuilder t = new StringBuilder();
    private List<String> cs = new ArrayList<>();
    private int idx = 0;

    public CombinationIterator(String characters, int combinationLength) {
        n = characters.length();
        this.combinationLength = combinationLength;
        this.characters = characters;
        dfs(0);
    }

    public String next() {
        return cs.get(idx++);
    }

    public boolean hasNext() {
        return idx < cs.size();
    }

    private void dfs(int i) {
        if (t.length() == combinationLength) {
            cs.add(t.toString());
            return;
        }
        if (i == n) {
            return;
        }
        t.append(characters.charAt(i));
        dfs(i + 1);
        t.deleteCharAt(t.length() - 1);
        dfs(i + 1);
    }
}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
```

```java
class CombinationIterator {
    private int curr;
    private int size;
    private char[] cs;

    public CombinationIterator(String characters, int combinationLength) {
        int n = characters.length();
        curr = (1 << n) - 1;
        size = combinationLength;
        cs = new char[n];
        for (int i = 0; i < n; ++i) {
            cs[i] = characters.charAt(n - i - 1);
        }
    }

    public String next() {
        while (curr >= 0 && Integer.bitCount(curr) != size) {
            --curr;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < cs.length; ++i) {
            if (((curr >> i) & 1) == 1) {
                ans.append(cs[i]);
            }
        }
        --curr;
        return ans.reverse().toString();
    }

    public boolean hasNext() {
        while (curr >= 0 && Integer.bitCount(curr) != size) {
            --curr;
        }
        return curr >= 0;
    }
}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
```

### **C++**

```cpp
class CombinationIterator {
public:
    string characters;
    vector<string> cs;
    int idx;
    int n;
    int combinationLength;
    string t;

    CombinationIterator(string characters, int combinationLength) {
        idx = 0;
        n = characters.size();
        this->characters = characters;
        this->combinationLength = combinationLength;
        dfs(0);
    }

    string next() {
        return cs[idx++];
    }

    bool hasNext() {
        return idx < cs.size();
    }

    void dfs(int i) {
        if (t.size() == combinationLength) {
            cs.push_back(t);
            return;
        }
        if (i == n) return;
        t.push_back(characters[i]);
        dfs(i + 1);
        t.pop_back();
        dfs(i + 1);
    }
};

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator* obj = new CombinationIterator(characters, combinationLength);
 * string param_1 = obj->next();
 * bool param_2 = obj->hasNext();
 */
```

```cpp
class CombinationIterator {
public:
    int size;
    string cs;
    int curr;

    CombinationIterator(string characters, int combinationLength) {
        int n = characters.size();
        curr = (1 << n) - 1;
        reverse(characters.begin(), characters.end());
        cs = characters;
        size = combinationLength;
    }

    string next() {
        while (curr >= 0 && __builtin_popcount(curr) != size) --curr;
        string ans;
        for (int i = 0; i < cs.size(); ++i) {
            if ((curr >> i) & 1) {
                ans += cs[i];
            }
        }
        reverse(ans.begin(), ans.end());
        --curr;
        return ans;
    }

    bool hasNext() {
        while (curr >= 0 && __builtin_popcount(curr) != size) --curr;
        return curr >= 0;
    }
};

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator* obj = new CombinationIterator(characters, combinationLength);
 * string param_1 = obj->next();
 * bool param_2 = obj->hasNext();
 */
```

### **Go**

```go
type CombinationIterator struct {
	cs  []string
	idx int
}

func Constructor(characters string, combinationLength int) CombinationIterator {
	t := []byte{}
	n := len(characters)
	cs := []string{}
	var dfs func(int)
	dfs = func(i int) {
		if len(t) == combinationLength {
			cs = append(cs, string(t))
			return
		}
		if i == n {
			return
		}
		t = append(t, characters[i])
		dfs(i + 1)
		t = t[:len(t)-1]
		dfs(i + 1)
	}
	dfs(0)
	return CombinationIterator{cs, 0}
}

func (this *CombinationIterator) Next() string {
	ans := this.cs[this.idx]
	this.idx++
	return ans
}

func (this *CombinationIterator) HasNext() bool {
	return this.idx < len(this.cs)
}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * obj := Constructor(characters, combinationLength);
 * param_1 := obj.Next();
 * param_2 := obj.HasNext();
 */
```

```go
type CombinationIterator struct {
	curr int
	size int
	cs   []byte
}

func Constructor(characters string, combinationLength int) CombinationIterator {
	n := len(characters)
	curr := (1 << n) - 1
	size := combinationLength
	cs := make([]byte, n)
	for i := range characters {
		cs[n-i-1] = characters[i]
	}
	return CombinationIterator{curr, size, cs}
}

func (this *CombinationIterator) Next() string {
	for this.curr >= 0 && bits.OnesCount(uint(this.curr)) != this.size {
		this.curr--
	}
	ans := []byte{}
	for i := range this.cs {
		if (this.curr >> i & 1) == 1 {
			ans = append(ans, this.cs[i])
		}
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	this.curr--
	return string(ans)
}

func (this *CombinationIterator) HasNext() bool {
	for this.curr >= 0 && bits.OnesCount(uint(this.curr)) != this.size {
		this.curr--
	}
	return this.curr >= 0
}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * obj := Constructor(characters, combinationLength);
 * param_1 := obj.Next();
 * param_2 := obj.HasNext();
 */
```

### **...**

```

```

<!-- tabs:end -->
