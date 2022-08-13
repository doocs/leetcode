# [2105. 给植物浇水 II](https://leetcode.cn/problems/watering-plants-ii)

[English Version](/solution/2100-2199/2105.Watering%20Plants%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Alice 和 Bob 打算给花园里的 <code>n</code> 株植物浇水。植物排成一行，从左到右进行标记，编号从 <code>0</code> 到 <code>n - 1</code> 。其中，第 <code>i</code> 株植物的位置是 <code>x = i</code> 。</p>

<p>每一株植物都需要浇特定量的水。Alice 和 Bob 每人有一个水罐，<strong>最初是满的 </strong>。他们按下面描述的方式完成浇水：</p>

<ul>
	<li>&nbsp;Alice 按 <strong>从左到右</strong> 的顺序给植物浇水，从植物 <code>0</code> 开始。Bob 按 <strong>从右到左</strong> 的顺序给植物浇水，从植物 <code>n - 1</code> 开始。他们 <strong>同时</strong> 给植物浇水。</li>
	<li>如果没有足够的水 <strong>完全</strong> 浇灌下一株植物，他 / 她会立即重新灌满浇水罐。</li>
	<li>不管植物需要多少水，浇水所耗费的时间都是一样的。</li>
	<li><strong>不能</strong> 提前重新灌满水罐。</li>
	<li>每株植物都可以由 Alice 或者 Bob 来浇水。</li>
	<li>如果 Alice 和 Bob 到达同一株植物，那么当前水罐中水更多的人会给这株植物浇水。如果他俩水量相同，那么 Alice 会给这株植物浇水。</li>
</ul>

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>plants</code> ，数组由 <code>n</code> 个整数组成。其中，<code>plants[i]</code> 为第 <code>i</code> 株植物需要的水量。另有两个整数 <code>capacityA</code> 和&nbsp;<code>capacityB</code> 分别表示 Alice 和 Bob 水罐的容量。返回两人浇灌所有植物过程中重新灌满水罐的 <strong>次数</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>plants = [2,2,3,3], capacityA = 5, capacityB = 5
<strong>输出：</strong>1
<strong>解释：</strong>
- 最初，Alice 和 Bob 的水罐中各有 5 单元水。
- Alice 给植物 0 浇水，Bob 给植物 3 浇水。
- Alice 和 Bob 现在分别剩下 3 单元和 2 单元水。
- Alice 有足够的水给植物 1 ，所以她直接浇水。Bob 的水不够给植物 2 ，所以他先重新装满水，再浇水。
所以，两人浇灌所有植物过程中重新灌满水罐的次数 = 0 + 0 + 1 + 0 = 1 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>plants = [2,2,3,3], capacityA = 3, capacityB = 4
<strong>输出：</strong>2
<strong>解释：</strong>
- 最初，Alice 的水罐中有 3 单元水，Bob 的水罐中有 4 单元水。
- Alice 给植物 0 浇水，Bob 给植物 3 浇水。
- Alice 和 Bob 现在都只有 1 单元水，并分别需要给植物 1 和植物 2 浇水。
- 由于他们的水量均不足以浇水，所以他们重新灌满水罐再进行浇水。
所以，两人浇灌所有植物过程中重新灌满水罐的次数 = 0 + 1 + 1 + 0 = 2 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>plants = [5], capacityA = 10, capacityB = 8
<strong>输出：</strong>0
<strong>解释：</strong>
- 只有一株植物
- Alice 的水罐有 10 单元水，Bob 的水罐有 8 单元水。因此 Alice 的水罐中水更多，她会给这株植物浇水。
所以，两人浇灌所有植物过程中重新灌满水罐的次数 = 0 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == plants.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= plants[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>max(plants[i]) &lt;= capacityA, capacityB &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

双指针直接模拟即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumRefill(self, plants: List[int], capacityA: int, capacityB: int) -> int:
        i, j = 0, len(plants) - 1
        ans = 0
        a, b = capacityA, capacityB
        while i <= j:
            if i == j:
                if max(capacityA, capacityB) < plants[i]:
                    ans += 1
                break
            if capacityA < plants[i]:
                capacityA = a - plants[i]
                ans += 1
            else:
                capacityA -= plants[i]
            if capacityB < plants[j]:
                capacityB = b - plants[j]
                ans += 1
            else:
                capacityB -= plants[j]
            i += 1
            j -= 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int i = 0, j = plants.length - 1;
        int ans = 0, a = capacityA, b = capacityB;
        while (i <= j) {
            if (i == j) {
                if (Math.max(capacityA, capacityB) < plants[i]) {
                    ++ans;
                }
                break;
            }
            if (capacityA < plants[i]) {
                capacityA = a - plants[i];
                ++ans;
            } else {
                capacityA -= plants[i];
            }
            if (capacityB < plants[j]) {
                capacityB = b - plants[j];
                ++ans;
            } else {
                capacityB -= plants[j];
            }
            ++i;
            --j;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumRefill(vector<int>& plants, int capacityA, int capacityB) {
        int i = 0, j = plants.size() - 1;
        int ans = 0, a = capacityA, b = capacityB;
        while (i <= j) {
            if (i == j) {
                if (max(capacityA, capacityB) < plants[i]) ++ans;
                break;
            }
            if (capacityA < plants[i]) {
                capacityA = a - plants[i];
                ++ans;
            } else
                capacityA -= plants[i];

            if (capacityB < plants[j]) {
                capacityB = b - plants[j];
                ++ans;
            } else
                capacityB -= plants[j];
            ++i;
            --j;
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumRefill(plants []int, capacityA int, capacityB int) int {
	i, j := 0, len(plants)-1
	ans, a, b := 0, capacityA, capacityB
	for i <= j {
		if i == j {
			if max(capacityA, capacityB) < plants[i] {
				ans++
			}
			break
		}
		if capacityA < plants[i] {
			capacityA = a - plants[i]
			ans++
		} else {
			capacityA -= plants[i]
		}
		if capacityB < plants[j] {
			capacityB = b - plants[j]
			ans++
		} else {
			capacityB -= plants[j]
		}
		i++
		j--
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
