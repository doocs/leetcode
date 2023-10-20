# [2525. Categorize Box According to Criteria](https://leetcode.com/problems/categorize-box-according-to-criteria)

[中文文档](/solution/2500-2599/2525.Categorize%20Box%20According%20to%20Criteria/README.md)

## Description

<p>Given four integers <code>length</code>, <code>width</code>, <code>height</code>, and <code>mass</code>, representing the dimensions and mass of a box, respectively, return <em>a string representing the <strong>category</strong> of the box</em>.</p>

<ul>
	<li>The box is <code>&quot;Bulky&quot;</code> if:
    <ul>
    	<li><strong>Any</strong> of the dimensions of the box is greater or equal to <code>10<sup>4</sup></code>.</li>
    	<li>Or, the <strong>volume</strong> of the box is greater or equal to <code>10<sup>9</sup></code>.</li>
    </ul>
    </li>
    <li>If the mass of the box is greater or equal to <code>100</code>, it is <code>&quot;Heavy&quot;.</code></li>
    <li>If the box is both <code>&quot;Bulky&quot;</code> and <code>&quot;Heavy&quot;</code>, then its category is <code>&quot;Both&quot;</code>.</li>
    <li>If the box is neither <code>&quot;Bulky&quot;</code> nor <code>&quot;Heavy&quot;</code>, then its category is <code>&quot;Neither&quot;</code>.</li>
    <li>If the box is <code>&quot;Bulky&quot;</code> but not <code>&quot;Heavy&quot;</code>, then its category is <code>&quot;Bulky&quot;</code>.</li>
    <li>If the box is <code>&quot;Heavy&quot;</code> but not <code>&quot;Bulky&quot;</code>, then its category is <code>&quot;Heavy&quot;</code>.</li>
</ul>

<p><strong>Note</strong> that the volume of the box is the product of its length, width and height.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> length = 1000, width = 35, height = 700, mass = 300
<strong>Output:</strong> &quot;Heavy&quot;
<strong>Explanation:</strong> 
None of the dimensions of the box is greater or equal to 10<sup>4</sup>. 
Its volume = 24500000 &lt;= 10<sup>9</sup>. So it cannot be categorized as &quot;Bulky&quot;.
However mass &gt;= 100, so the box is &quot;Heavy&quot;.
Since the box is not &quot;Bulky&quot; but &quot;Heavy&quot;, we return &quot;Heavy&quot;.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> length = 200, width = 50, height = 800, mass = 50
<strong>Output:</strong> &quot;Neither&quot;
<strong>Explanation:</strong> 
None of the dimensions of the box is greater or equal to 10<sup>4</sup>.
Its volume = 8 * 10<sup>6</sup> &lt;= 10<sup>9</sup>. So it cannot be categorized as &quot;Bulky&quot;.
Its mass is also less than 100, so it cannot be categorized as &quot;Heavy&quot; either. 
Since its neither of the two above categories, we return &quot;Neither&quot;.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= length, width, height &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= mass &lt;= 10<sup>3</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
	bulky := length >= 1e4 || width >= 1e4 || height >= 1e4 || v >= 1e9
	heavy := mass >= 100
	if bulky && heavy {
		return "Both"
	}
	if bulky {
		return "Bulky"
	}
	if heavy {
		return "Heavy"
	}
	return "Neither"
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
