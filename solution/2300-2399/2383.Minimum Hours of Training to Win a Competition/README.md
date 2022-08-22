# [2383. 赢得比赛需要的最少训练时长](https://leetcode.cn/problems/minimum-hours-of-training-to-win-a-competition)

[English Version](/solution/2300-2399/2383.Minimum%20Hours%20of%20Training%20to%20Win%20a%20Competition/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你正在参加一场比赛，给你两个 <strong>正</strong> 整数 <code>initialEnergy</code> 和 <code>initialExperience</code> 分别表示你的初始精力和初始经验。</p>

<p>另给你两个下标从 <strong>0</strong> 开始的整数数组 <code>energy</code> 和 <code>experience</code>，长度均为 <code>n</code> 。</p>

<p>你将会 <strong>依次</strong> 对上 <code>n</code> 个对手。第 <code>i</code> 个对手的精力和经验分别用 <code>energy[i]</code> 和 <code>experience[i]</code> 表示。当你对上对手时，需要在经验和精力上都 <strong>严格</strong> 超过对手才能击败他们，然后在可能的情况下继续对上下一个对手。</p>

<p>击败第 <code>i</code> 个对手会使你的经验 <strong>增加</strong> <code>experience[i]</code>，但会将你的精力 <strong>减少</strong>&nbsp; <code>energy[i]</code> 。</p>

<p>在开始比赛前，你可以训练几个小时。每训练一个小时，你可以选择将增加经验增加 1 <strong>或者</strong> 将精力增加 1 。</p>

<p>返回击败全部 <code>n</code> 个对手需要训练的 <strong>最少</strong> 小时数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>initialEnergy = 5, initialExperience = 3, energy = [1,4,3,2], experience = [2,6,3,1]
<strong>输出：</strong>8
<strong>解释：</strong>在 6 小时训练后，你可以将精力提高到 11 ，并且再训练 2 个小时将经验提高到 5 。
按以下顺序与对手比赛：
- 你的精力与经验都超过第 0 个对手，所以获胜。
  精力变为：11 - 1 = 10 ，经验变为：5 + 2 = 7 。
- 你的精力与经验都超过第 1 个对手，所以获胜。
  精力变为：10 - 4 = 6 ，经验变为：7 + 6 = 13 。
- 你的精力与经验都超过第 2 个对手，所以获胜。
  精力变为：6 - 3 = 3 ，经验变为：13 + 3 = 16 。
- 你的精力与经验都超过第 3 个对手，所以获胜。
  精力变为：3 - 2 = 1 ，经验变为：16 + 1 = 17 。
在比赛前进行了 8 小时训练，所以返回 8 。
可以证明不存在更小的答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>initialEnergy = 2, initialExperience = 4, energy = [1], experience = [3]
<strong>输出：</strong>0
<strong>解释：</strong>你不需要额外的精力和经验就可以赢得比赛，所以返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == energy.length == experience.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= initialEnergy, initialExperience, energy[i], experience[i] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 模拟**

对上对手时，需要满足经验和精力都严格超过对手，因此，我们遍历 $n$ 个对手，若经验或者精力不足以超过对手，则补到刚好能超过（每次训练，可以增加 $1$）。然后增加对应的经验值，减少对应的精力值。

遍历结束，返回训练的小时数。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 是对手的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minNumberOfHours(
        self,
        initialEnergy: int,
        initialExperience: int,
        energy: List[int],
        experience: List[int],
    ) -> int:
        ans = 0
        for a, b in zip(energy, experience):
            if initialEnergy <= a:
                ans += a - initialEnergy + 1
                initialEnergy = a + 1
            if initialExperience <= b:
                ans += b - initialExperience + 1
                initialExperience = b + 1
            initialEnergy -= a
            initialExperience += b
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int ans = 0;
        for (int i = 0; i < energy.length; ++i) {
            int a = energy[i], b = experience[i];
            if (initialEnergy <= a) {
                ans += a - initialEnergy + 1;
                initialEnergy = a + 1;
            }
            if (initialExperience <= b) {
                ans += b - initialExperience + 1;
                initialExperience = b + 1;
            }
            initialEnergy -= a;
            initialExperience += b;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minNumberOfHours(int initialEnergy, int initialExperience, vector<int>& energy, vector<int>& experience) {
        int ans = 0;
        for (int i = 0; i < energy.size(); ++i) {
            int a = energy[i], b = experience[i];
            if (initialEnergy <= a) {
                ans += a - initialEnergy + 1;
                initialEnergy = a + 1;
            }
            if (initialExperience <= b) {
                ans += b - initialExperience + 1;
                initialExperience = b + 1;
            }
            initialEnergy -= a;
            initialExperience += b;
        }
        return ans;
    }
};
```

### **Go**

```go
func minNumberOfHours(initialEnergy int, initialExperience int, energy []int, experience []int) int {
	ans := 0
	for i, a := range energy {
		b := experience[i]
		if initialEnergy <= a {
			ans += a - initialEnergy + 1
			initialEnergy = a + 1
		}
		if initialExperience <= b {
			ans += b - initialExperience + 1
			initialExperience = b + 1
		}
		initialEnergy -= a
		initialExperience += b
	}
	return ans
}
```

### **TypeScript**

```ts
function minNumberOfHours(
    initialEnergy: number,
    initialExperience: number,
    energy: number[],
    experience: number[],
): number {
    const n = energy.length;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        const minEnergy = energy[i];
        const minExperience = experience[i];
        if (initialEnergy <= minEnergy) {
            const need = minEnergy - initialEnergy + 1;
            ans += need;
            initialEnergy += need;
        }
        if (initialExperience <= minExperience) {
            const need = minExperience - initialExperience + 1;
            ans += need;
            initialExperience += need;
        }
        initialEnergy -= minEnergy;
        initialExperience += minExperience;
    }
    return ans;
}
```

```ts
function minNumberOfHours(
    initialEnergy: number,
    initialExperience: number,
    energy: number[],
    experience: number[],
): number {
    let res = 0;
    for (const v of experience) {
        if (initialExperience <= v) {
            res += v - initialExperience + 1;
            initialExperience = v + 1;
        }
        initialExperience += v;
    }
    for (const v of energy) {
        if (initialEnergy <= v) {
            res += v - initialEnergy + 1;
            initialEnergy = v + 1;
        }
        initialEnergy -= v;
    }
    return res;
}
```

### **C**

```c
int minNumberOfHours(int initialEnergy, int initialExperience, int* energy, int energySize, int* experience, int experienceSize) {
    int res = 0;
    for (int i = 0; i < energySize; i++) {
        if (initialEnergy <= energy[i]) {
            res += energy[i] - initialEnergy + 1;
            initialEnergy = energy[i] + 1;
        }
        if (initialExperience <= experience[i]) {
            res += experience[i] - initialExperience + 1;
            initialExperience = experience[i] + 1;
        }
        initialEnergy -= energy[i];
        initialExperience += experience[i];
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_number_of_hours(
        mut initial_energy: i32,
        mut initial_experience: i32,
        energy: Vec<i32>,
        experience: Vec<i32>,
    ) -> i32 {
        let n = energy.len();
        let mut res = 0;
        for i in 0..n {
            if initial_energy <= energy[i] {
                res += energy[i] - initial_energy + 1;
                initial_energy = energy[i] + 1;
            }
            if initial_experience <= experience[i] {
                res += experience[i] - initial_experience + 1;
                initial_experience = experience[i] + 1;
            }
            initial_energy -= energy[i];
            initial_experience += experience[i];
        }
        res
    }
}
```

### **...**

```


```

<!-- tabs:end -->
