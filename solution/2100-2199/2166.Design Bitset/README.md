# [2166. 设计位集](https://leetcode.cn/problems/design-bitset)

[English Version](/solution/2100-2199/2166.Design%20Bitset/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>位集 Bitset</strong> 是一种能以紧凑形式存储位的数据结构。</p>

<p>请你实现 <code>Bitset</code> 类。</p>

<ul>
	<li><code>Bitset(int size)</code> 用 <code>size</code> 个位初始化 Bitset ，所有位都是 <code>0</code> 。</li>
	<li><code>void fix(int idx)</code> 将下标为 <code>idx</code> 的位上的值更新为 <code>1</code> 。如果值已经是 <code>1</code> ，则不会发生任何改变。</li>
	<li><code>void unfix(int idx)</code> 将下标为 <code>idx</code> 的位上的值更新为 <code>0</code> 。如果值已经是 <code>0</code> ，则不会发生任何改变。</li>
	<li><code>void flip()</code> 翻转 Bitset 中每一位上的值。换句话说，所有值为 <code>0</code> 的位将会变成 <code>1</code> ，反之亦然。</li>
	<li><code>boolean all()</code> 检查&nbsp;Bitset 中 <strong>每一位</strong> 的值是否都是 <code>1</code> 。如果满足此条件，返回 <code>true</code> ；否则，返回 <code>false</code> 。</li>
	<li><code>boolean one()</code> 检查&nbsp;Bitset 中 是否&nbsp;<strong>至少一位</strong> 的值是 <code>1</code> 。如果满足此条件，返回 <code>true</code> ；否则，返回 <code>false</code> 。</li>
	<li><code>int count()</code> 返回 Bitset 中值为 1 的位的 <strong>总数</strong> 。</li>
	<li><code>String toString()</code> 返回 Bitset 的当前组成情况。注意，在结果字符串中，第 <code>i</code> 个下标处的字符应该与 Bitset 中的第 <code>i</code> 位一致。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入</strong>
["Bitset", "fix", "fix", "flip", "all", "unfix", "flip", "one", "unfix", "count", "toString"]
[[5], [3], [1], [], [], [0], [], [], [0], [], []]
<strong>输出</strong>
[null, null, null, null, false, null, null, true, null, 2, "01010"]

<strong>解释</strong>
Bitset bs = new Bitset(5); // bitset = "00000".
bs.fix(3);     // 将 idx = 3 处的值更新为 1 ，此时 bitset = "00010" 。
bs.fix(1);     // 将 idx = 1 处的值更新为 1 ，此时 bitset = "01010" 。
bs.flip();     // 翻转每一位上的值，此时 bitset = "10101" 。
bs.all();      // 返回 False ，bitset 中的值不全为 1 。
bs.unfix(0);   // 将 idx = 0 处的值更新为 0 ，此时 bitset = "00101" 。
bs.flip();     // 翻转每一位上的值，此时 bitset = "11010" 。
bs.one();      // 返回 True ，至少存在一位的值为 1 。
bs.unfix(0);   // 将 idx = 0 处的值更新为 0 ，此时 bitset = "01010" 。
bs.count();    // 返回 2 ，当前有 2 位的值为 1 。
bs.toString(); // 返回 "01010" ，即 bitset 的当前组成情况。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= size &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= idx &lt;= size - 1</code></li>
	<li>至多调用&nbsp;<code>fix</code>、<code>unfix</code>、<code>flip</code>、<code>all</code>、<code>one</code>、<code>count</code> 和 <code>toString</code> 方法 <strong>总共</strong> <code>10<sup>5</sup></code> 次</li>
	<li>至少调用 <code>all</code>、<code>one</code>、<code>count</code> 或 <code>toString</code> 方法一次</li>
	<li>至多调用&nbsp;<code>toString</code> 方法 <code>5</code> 次</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Bitset:
    def __init__(self, size: int):
        self.a = ['0'] * size
        self.b = ['1'] * size
        self.cnt = 0

    def fix(self, idx: int) -> None:
        if self.a[idx] == '0':
            self.a[idx] = '1'
            self.cnt += 1
        self.b[idx] = '0'

    def unfix(self, idx: int) -> None:
        if self.a[idx] == '1':
            self.a[idx] = '0'
            self.cnt -= 1
        self.b[idx] = '1'

    def flip(self) -> None:
        self.a, self.b = self.b, self.a
        self.cnt = len(self.a) - self.cnt

    def all(self) -> bool:
        return self.cnt == len(self.a)

    def one(self) -> bool:
        return self.cnt > 0

    def count(self) -> int:
        return self.cnt

    def toString(self) -> str:
        return ''.join(self.a)


# Your Bitset object will be instantiated and called as such:
# obj = Bitset(size)
# obj.fix(idx)
# obj.unfix(idx)
# obj.flip()
# param_4 = obj.all()
# param_5 = obj.one()
# param_6 = obj.count()
# param_7 = obj.toString()
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Bitset {
    private char[] a;
    private char[] b;
    private int cnt;

    public Bitset(int size) {
        a = new char[size];
        b = new char[size];
        Arrays.fill(a, '0');
        Arrays.fill(b, '1');
    }

    public void fix(int idx) {
        if (a[idx] == '0') {
            a[idx] = '1';
            ++cnt;
        }
        b[idx] = '0';
    }

    public void unfix(int idx) {
        if (a[idx] == '1') {
            a[idx] = '0';
            --cnt;
        }
        b[idx] = '1';
    }

    public void flip() {
        char[] t = a;
        a = b;
        b = t;
        cnt = a.length - cnt;
    }

    public boolean all() {
        return cnt == a.length;
    }

    public boolean one() {
        return cnt > 0;
    }

    public int count() {
        return cnt;
    }

    public String toString() {
        return String.valueOf(a);
    }
}

/**
 * Your Bitset object will be instantiated and called as such:
 * Bitset obj = new Bitset(size);
 * obj.fix(idx);
 * obj.unfix(idx);
 * obj.flip();
 * boolean param_4 = obj.all();
 * boolean param_5 = obj.one();
 * int param_6 = obj.count();
 * String param_7 = obj.toString();
 */
```

### **C++**

```cpp
class Bitset {
public:
    string a, b;
    int cnt = 0;

    Bitset(int size) {
        a = string(size, '0');
        b = string(size, '1');
    }

    void fix(int idx) {
        if (a[idx] == '0') a[idx] = '1', ++cnt;
        b[idx] = '0';
    }

    void unfix(int idx) {
        if (a[idx] == '1') a[idx] = '0', --cnt;
        b[idx] = '1';
    }

    void flip() {
        swap(a, b);
        cnt = a.size() - cnt;
    }

    bool all() {
        return cnt == a.size();
    }

    bool one() {
        return cnt > 0;
    }

    int count() {
        return cnt;
    }

    string toString() {
        return a;
    }
};

/**
 * Your Bitset object will be instantiated and called as such:
 * Bitset* obj = new Bitset(size);
 * obj->fix(idx);
 * obj->unfix(idx);
 * obj->flip();
 * bool param_4 = obj->all();
 * bool param_5 = obj->one();
 * int param_6 = obj->count();
 * string param_7 = obj->toString();
 */
```

### **Go**

```go
type Bitset struct {
	a   []byte
	b   []byte
	cnt int
}

func Constructor(size int) Bitset {
	a := bytes.Repeat([]byte{'0'}, size)
	b := bytes.Repeat([]byte{'1'}, size)
	return Bitset{a, b, 0}
}

func (this *Bitset) Fix(idx int) {
	if this.a[idx] == '0' {
		this.a[idx] = '1'
		this.cnt++
	}
	this.b[idx] = '0'
}

func (this *Bitset) Unfix(idx int) {
	if this.a[idx] == '1' {
		this.a[idx] = '0'
		this.cnt--
	}
	this.b[idx] = '1'
}

func (this *Bitset) Flip() {
	this.a, this.b = this.b, this.a
	this.cnt = len(this.a) - this.cnt
}

func (this *Bitset) All() bool {
	return this.cnt == len(this.a)
}

func (this *Bitset) One() bool {
	return this.cnt > 0
}

func (this *Bitset) Count() int {
	return this.cnt
}

func (this *Bitset) ToString() string {
	return string(this.a)
}

/**
 * Your Bitset object will be instantiated and called as such:
 * obj := Constructor(size);
 * obj.Fix(idx);
 * obj.Unfix(idx);
 * obj.Flip();
 * param_4 := obj.All();
 * param_5 := obj.One();
 * param_6 := obj.Count();
 * param_7 := obj.ToString();
 */
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
