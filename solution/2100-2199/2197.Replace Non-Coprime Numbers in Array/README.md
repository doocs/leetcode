# [2197. 替换数组中的非互质数](https://leetcode.cn/problems/replace-non-coprime-numbers-in-array)

[English Version](/solution/2100-2199/2197.Replace%20Non-Coprime%20Numbers%20in%20Array/README_EN.md)

<!-- tags:栈,数组,数学,数论 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 。请你对数组执行下述操作：</p>

<ol>
	<li>从 <code>nums</code> 中找出 <strong>任意</strong> 两个 <strong>相邻</strong> 的 <strong>非互质</strong> 数。</li>
	<li>如果不存在这样的数，<strong>终止</strong> 这一过程。</li>
	<li>否则，删除这两个数，并 <strong>替换</strong> 为它们的 <strong>最小公倍数</strong>（Least Common Multiple，LCM）。</li>
	<li>只要还能找出两个相邻的非互质数就继续 <strong>重复</strong> 这一过程。</li>
</ol>

<p>返回修改后得到的 <strong>最终</strong> 数组。可以证明的是，以 <strong>任意</strong> 顺序替换相邻的非互质数都可以得到相同的结果。</p>

<p>生成的测试用例可以保证最终数组中的值 <strong>小于或者等于</strong> <code>10<sup>8</sup></code> 。</p>

<p>两个数字 <code>x</code> 和 <code>y</code> 满足 <strong>非互质数</strong> 的条件是：<code>GCD(x, y) &gt; 1</code> ，其中 <code>GCD(x, y)</code> 是 <code>x</code> 和 <code>y</code> 的 <strong>最大公约数</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1 ：</strong></p>

<pre>
<strong>输入：</strong>nums = [6,4,3,2,7,6,2]
<strong>输出：</strong>[12,7,6]
<strong>解释：</strong>
- (6, 4) 是一组非互质数，且 LCM(6, 4) = 12 。得到 nums = [<em><strong>12</strong></em>,3,2,7,6,2] 。
- (12, 3) 是一组非互质数，且 LCM(12, 3) = 12 。得到 nums = [<em><strong>12</strong></em>,2,7,6,2] 。
- (12, 2) 是一组非互质数，且 LCM(12, 2) = 12 。得到 nums = [<em><strong>12</strong></em>,7,6,2] 。
- (6, 2) 是一组非互质数，且 LCM(6, 2) = 6 。得到 nums = [12,7,<em><strong>6</strong></em>] 。
现在，nums 中不存在相邻的非互质数。
因此，修改后得到的最终数组是 [12,7,6] 。
注意，存在其他方法可以获得相同的最终数组。
</pre>

<p><strong>示例 2 ：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,1,1,3,3,3]
<strong>输出：</strong>[2,1,1,3]
<strong>解释：</strong>
- (3, 3) 是一组非互质数，且 LCM(3, 3) = 3 。得到 nums = [2,2,1,1,<em><strong>3</strong></em>,3] 。
- (3, 3) 是一组非互质数，且 LCM(3, 3) = 3 。得到 nums = [2,2,1,1,<em><strong>3</strong></em>] 。
- (2, 2) 是一组非互质数，且 LCM(2, 2) = 2 。得到 nums = [<em><strong>2</strong></em>,1,1,3] 。
现在，nums 中不存在相邻的非互质数。 
因此，修改后得到的最终数组是 [2,1,1,3] 。 
注意，存在其他方法可以获得相同的最终数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li>生成的测试用例可以保证最终数组中的值 <strong>小于或者等于</strong> <code>10<sup>8</sup></code> 。</li>
</ul>

## 解法

### 方法一：栈

如果存在三个相邻的数 $x$, $y$, $z$ 可以进行合并，那么我们先合并 $x$ 和 $y$，再合并 $z$ 的结果，与先合并 $y$ 和 $z$，再合并 $x$ 的结果是一样的，结果均为 $\text{LCM}(x, y, z)$。

因此，我们可以总是优先合并左侧相邻的数，再将合并后的结果与右侧相邻的数进行合并。

我们使用一个栈来模拟这个过程，遍历数组，对于每个数，我们将其入栈，然后不断检查栈顶的两个数是否互质，如果不互质，我们将这两个数出栈，然后将它们的最小公倍数入栈，直到栈顶的两个数互质，或者栈中元素小于两个。

最后栈中的元素即为最终结果。

时间复杂度 $O(n \times \log M)$，空间复杂度 $O(n)$。其中 $M$ 为数组中的最大值。

<!-- tabs:start -->

```python
class Solution:
    def replaceNonCoprimes(self, nums: List[int]) -> List[int]:
        stk = []
        for x in nums:
            stk.append(x)
            while len(stk) > 1:
                x, y = stk[-2:]
                g = gcd(x, y)
                if g == 1:
                    break
                stk.pop()
                stk[-1] = x * y // g
        return stk
```

```java
class Solution {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        List<Integer> stk = new ArrayList<>();
        for (int x : nums) {
            stk.add(x);
            while (stk.size() > 1) {
                x = stk.get(stk.size() - 1);
                int y = stk.get(stk.size() - 2);
                int g = gcd(x, y);
                if (g == 1) {
                    break;
                }
                stk.remove(stk.size() - 1);
                stk.set(stk.size() - 1, (int) ((long) x * y / g));
            }
        }
        return stk;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
```

```cpp
class Solution {
public:
    vector<int> replaceNonCoprimes(vector<int>& nums) {
        vector<int> stk;
        for (int x : nums) {
            stk.push_back(x);
            while (stk.size() > 1) {
                x = stk.back();
                int y = stk[stk.size() - 2];
                int g = __gcd(x, y);
                if (g == 1) {
                    break;
                }
                stk.pop_back();
                stk.back() = 1LL * x * y / g;
            }
        }
        return stk;
    }
};
```

```go
func replaceNonCoprimes(nums []int) []int {
	stk := []int{}
	for _, x := range nums {
		stk = append(stk, x)
		for len(stk) > 1 {
			x = stk[len(stk)-1]
			y := stk[len(stk)-2]
			g := gcd(x, y)
			if g == 1 {
				break
			}
			stk = stk[:len(stk)-1]
			stk[len(stk)-1] = x * y / g
		}
	}
	return stk
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

```ts
function replaceNonCoprimes(nums: number[]): number[] {
    const gcd = (a: number, b: number): number => {
        if (b === 0) {
            return a;
        }
        return gcd(b, a % b);
    };
    const stk: number[] = [];
    for (let x of nums) {
        stk.push(x);
        while (stk.length > 1) {
            x = stk.at(-1)!;
            const y = stk.at(-2)!;
            const g = gcd(x, y);
            if (g === 1) {
                break;
            }
            stk.pop();
            stk.pop();
            stk.push(((x * y) / g) | 0);
        }
    }
    return stk;
}
```

<!-- tabs:end -->

<!-- end -->
