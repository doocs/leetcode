# [2525. 根据规则将箱子分类](https://leetcode.cn/problems/categorize-box-according-to-criteria)

[English Version](/solution/2500-2599/2525.Categorize%20Box%20According%20to%20Criteria/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你四个整数&nbsp;<code>length</code>&nbsp;，<code>width</code>&nbsp;，<code>height</code>&nbsp;和&nbsp;<code>mass</code>&nbsp;，分别表示一个箱子的三个维度和质量，请你返回一个表示箱子 <strong>类别</strong> 的字符串。</p>

<ul>
	<li>如果满足以下条件，那么箱子是&nbsp;<code>"Bulky"</code>&nbsp;的：
    <ul>
    	<li>箱子 <strong>至少有一个</strong> 维度大于等于 <code>10<sup>4</sup></code>&nbsp;。</li>
    	<li>或者箱子的 <strong>体积</strong> 大于等于&nbsp;<code>10<sup>9</sup></code>&nbsp;。</li>
    </ul>
    </li>
    <li>如果箱子的质量大于等于&nbsp;<code>100</code>&nbsp;，那么箱子是&nbsp;<code>"Heavy"</code>&nbsp;的。</li>
    <li>如果箱子同时是&nbsp;<code>"Bulky"</code> 和&nbsp;<code>"Heavy"</code>&nbsp;，那么返回类别为&nbsp;<code>"Both"</code>&nbsp;。</li>
    <li>如果箱子既不是&nbsp;<code>"Bulky"</code>&nbsp;，也不是&nbsp;<code>"Heavy"</code>&nbsp;，那么返回类别为&nbsp;<code>"Neither"</code>&nbsp;。</li>
    <li>如果箱子是&nbsp;<code>"Bulky"</code>&nbsp;但不是&nbsp;<code>"Heavy"</code>&nbsp;，那么返回类别为&nbsp;<code>"Bulky"</code>&nbsp;。</li>
    <li>如果箱子是&nbsp;<code>"Heavy"</code>&nbsp;但不是&nbsp;<code>"Bulky"</code>&nbsp;，那么返回类别为&nbsp;<code>"Heavy"</code>&nbsp;。</li>
</ul>

<p><strong>注意</strong>，箱子的体积等于箱子的长度、宽度和高度的乘积。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>length = 1000, width = 35, height = 700, mass = 300
<b>输出：</b>"Heavy"
<b>解释：</b>
箱子没有任何维度大于等于 10<sup>4 </sup>。
体积为 24500000 &lt;= 10<sup>9 </sup>。所以不能归类为 "Bulky" 。
但是质量 &gt;= 100 ，所以箱子是 "Heavy" 的。
由于箱子不是 "Bulky" 但是是 "Heavy" ，所以我们返回 "Heavy" 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>length = 200, width = 50, height = 800, mass = 50
<b>输出：</b>"Neither"
<b>解释：</b>
箱子没有任何维度大于等于 10<sup>4</sup>&nbsp;。
体积为 8 * 10<sup>6</sup> &lt;= 10<sup>9</sup>&nbsp;。所以不能归类为 "Bulky" 。
质量小于 100 ，所以不能归类为 "Heavy" 。
由于不属于上述两者任何一类，所以我们返回 "Neither" 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= length, width, height &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= mass &lt;= 10<sup>3</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

根据题意模拟即可。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def categorizeBox(self, length: int, width: int, height: int, mass: int) -> str:
        v = length * width * height
        bulky = int(any(x >= 10000 for x in (length, width, height)) or v >= 10**9)
        heavy = int(mass >= 100)
        i = heavy << 1 | bulky
        d = ['Neither', 'Bulky', 'Heavy', 'Both']
        return d[i]
```

```python
class Solution:
    def categorizeBox(self, length: int, width: int, height: int, mass: int) -> str:
        v = length * width * height
        bulky = any(x >= 10000 for x in (length, width, height)) or v >= 10**9
        heavy = mass >= 100

        if bulky and heavy:
            return "Both"
        if bulky:
            return "Bulky"
        if heavy:
            return "Heavy"

        return "Neither"
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String categorizeBox(int length, int width, int height, int mass) {
        long v = (long) length * width * height;
        int bulky = length >= 10000 || width >= 10000 || height >= 10000 || v >= 1000000000 ? 1 : 0;
        int heavy = mass >= 100 ? 1 : 0;
        String[] d = {"Neither", "Bulky", "Heavy", "Both"};
        int i = heavy << 1 | bulky;
        return d[i];
    }
}
```

```java
class Solution {
    public String categorizeBox(int length, int width, int height, int mass) {
        long v = (long) length * width * height;
        boolean bulky = length >= 1e4 || width >= 1e4 || height >= 1e4 || v >= 1e9;
        boolean heavy = mass >= 100;

        if (bulky && heavy) {
            return "Both";
        }
        if (bulky) {
            return "Bulky";
        }
        if (heavy) {
            return "Heavy";
        }

        return "Neither";
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string categorizeBox(int length, int width, int height, int mass) {
        long v = (long) length * width * height;
        int bulky = length >= 10000 || width >= 10000 || height >= 10000 || v >= 1000000000 ? 1 : 0;
        int heavy = mass >= 100 ? 1 : 0;
        string d[4] = {"Neither", "Bulky", "Heavy", "Both"};
        int i = heavy << 1 | bulky;
        return d[i];
    }
};
```

```cpp
class Solution {
public:
    string categorizeBox(int length, int width, int height, int mass) {
    long v = (long) length * width * height;
    bool bulky = length >= 1e4 || width >= 1e4 || height >= 1e4 || v >= 1e9;
    bool heavy = mass >= 100;

    if (bulky && heavy) {
        return "Both";
    }
    if (bulky) {
        return "Bulky";
    }
    if (heavy) {
        return "Heavy";
    }

    return "Neither";
    }
};
```

### **Go**

```go
func categorizeBox(length int, width int, height int, mass int) string {
	v := length * width * height
	i := 0
	if length >= 10000 || width >= 10000 || height >= 10000 || v >= 1000000000 {
		i |= 1
	}
	if mass >= 100 {
		i |= 2
	}
	d := [4]string{"Neither", "Bulky", "Heavy", "Both"}
	return d[i]
}
```

```go
func categorizeBox(length int, width int, height int, mass int) string {
	v := length * width * height
	bulky := length >= 1e4 || width >= 1e4  || height >= 1e4 || v >= 1e9
	heavy := mass >= 100
	if bulky && heavy {
				return "Both";
	}
	if bulky {
				return "Bulky";
	}
	if heavy {
			return "Heavy";
	}
	return "Neither";
}
```

### **TypeScript**

```ts
function categorizeBox(length: number, width: number, height: number, mass: number): string {
    const v = length * width * height;
    let i = 0;
    if (length >= 10000 || width >= 10000 || height >= 10000 || v >= 1000000000) {
        i |= 1;
    }
    if (mass >= 100) {
        i |= 2;
    }
    return ['Neither', 'Bulky', 'Heavy', 'Both'][i];
}
```

```ts
function categorizeBox(length: number, width: number, height: number, mass: number): string {
    const v = length * width * height;
    const bulky = length >= 1e4 || width >= 1e4 || height >= 1e4 || v >= 1e9;
    const heavy = mass >= 100;
    if (bulky && heavy) {
        return 'Both';
    }
    if (bulky) {
        return 'Bulky';
    }
    if (heavy) {
        return 'Heavy';
    }
    return 'Neither';
}
```

### **Rust**

```rust
impl Solution {
    pub fn categorize_box(length: i32, width: i32, height: i32, mass: i32) -> String {
        let v = length as i64 * width as i64 * height as i64;
        let mut i = 0;

        if length >= 10000 || width >= 10000 || height >= 10000 || v >= 1000000000 {
            i |= 1;
        }

        if mass >= 100 {
            i |= 2;
        }

        let d = vec!["Neither", "Bulky", "Heavy", "Both"];
        d[i].to_string()
    }
}
```

```rust
impl Solution {
    pub fn categorize_box(length: i32, width: i32, height: i32, mass: i32) -> String {
    let v = length * width * height;
    let bulky = length >= 10000 || width >= 10000 || height >= 10000 || length as i64 * width as i64 * height as i64 >= 1000000000;

    let heavy = mass >= 100;

    if bulky && heavy {
        return "Both".to_string();
    }
    if bulky {
        return "Bulky".to_string();
    }
    if heavy {
        return "Heavy".to_string();
    }

    "Neither".to_string()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
