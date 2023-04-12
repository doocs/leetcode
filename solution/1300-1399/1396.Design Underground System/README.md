# [1396. 设计地铁系统](https://leetcode.cn/problems/design-underground-system)

[English Version](/solution/1300-1399/1396.Design%20Underground%20System/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>地铁系统跟踪不同车站之间的乘客出行时间，并使用这一数据来计算从一站到另一站的平均时间。</p>

<p>实现 <code>UndergroundSystem</code> 类：</p>

<ul>
	<li><code>void checkIn(int id, string stationName, int t)</code>
    <ul>
    	<li>通行卡 ID 等于 <code>id</code> 的乘客，在时间 <code>t</code> ，从 <code>stationName</code> 站进入</li>
    	<li>乘客一次只能从一个站进入</li>
    </ul>
    </li>
    <li><code>void checkOut(int id, string stationName, int t)</code>
    <ul>
    	<li>通行卡 ID 等于 <code>id</code> 的乘客，在时间 <code>t</code> ，从 <code>stationName</code> 站离开</li>
    </ul>
    </li>
    <li><code>double getAverageTime(string startStation, string endStation)</code>
    <ul>
    	<li>返回从 <code>startStation</code> 站到 <code>endStation</code> 站的平均时间</li>
    	<li>平均时间会根据截至目前所有从 <code>startStation</code> 站 <strong>直接</strong> 到达 <code>endStation</code> 站的行程进行计算，也就是从 <code>startStation</code> 站进入并从 <code>endStation</code> 离开的行程</li>
    	<li>从 <code>startStation</code> 到 <code>endStation</code> 的行程时间与从 <code>endStation</code> 到 <code>startStation</code> 的行程时间可能不同</li>
    	<li>在调用 <code>getAverageTime</code> 之前，至少有一名乘客从 <code>startStation</code> 站到达 <code>endStation</code> 站</li>
    </ul>
    </li>
</ul>

<p>你可以假设对 <code>checkIn</code> 和 <code>checkOut</code> 方法的所有调用都是符合逻辑的。如果一名乘客在时间 <code>t<sub>1</sub></code> 进站、时间 <code>t<sub>2</sub></code> 出站，那么 <code>t<sub>1</sub> &lt; t<sub>2</sub></code> 。所有时间都按时间顺序发生。</p>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入</strong>
["UndergroundSystem","checkIn","checkIn","checkIn","checkOut","checkOut","checkOut","getAverageTime","getAverageTime","checkIn","getAverageTime","checkOut","getAverageTime"]
[[],[45,"Leyton",3],[32,"Paradise",8],[27,"Leyton",10],[45,"Waterloo",15],[27,"Waterloo",20],[32,"Cambridge",22],["Paradise","Cambridge"],["Leyton","Waterloo"],[10,"Leyton",24],["Leyton","Waterloo"],[10,"Waterloo",38],["Leyton","Waterloo"]]

<strong>输出</strong>
[null,null,null,null,null,null,null,14.00000,11.00000,null,11.00000,null,12.00000]

<strong>解释</strong>
UndergroundSystem undergroundSystem = new UndergroundSystem();
undergroundSystem.checkIn(45, "Leyton", 3);
undergroundSystem.checkIn(32, "Paradise", 8);
undergroundSystem.checkIn(27, "Leyton", 10);
undergroundSystem.checkOut(45, "Waterloo", 15);  // 乘客 45 "Leyton" -&gt; "Waterloo" ，用时 15-3 = 12
undergroundSystem.checkOut(27, "Waterloo", 20);  // 乘客 27 "Leyton" -&gt; "Waterloo" ，用时 20-10 = 10
undergroundSystem.checkOut(32, "Cambridge", 22); // 乘客 32 "Paradise" -&gt; "Cambridge" ，用时 22-8 = 14
undergroundSystem.getAverageTime("Paradise", "Cambridge"); // 返回 14.00000 。只有一个 "Paradise" -&gt; "Cambridge" 的行程，(14) / 1 = 14
undergroundSystem.getAverageTime("Leyton", "Waterloo");    // 返回 11.00000 。有两个 "Leyton" -&gt; "Waterloo" 的行程，(10 + 12) / 2 = 11
undergroundSystem.checkIn(10, "Leyton", 24);
undergroundSystem.getAverageTime("Leyton", "Waterloo");    // 返回 11.00000
undergroundSystem.checkOut(10, "Waterloo", 38);  // 乘客 10 "Leyton" -&gt; "Waterloo" ，用时 38-24 = 14
undergroundSystem.getAverageTime("Leyton", "Waterloo");    // 返回 12.00000 。有三个 "Leyton" -&gt; "Waterloo" 的行程，(10 + 12 + 14) / 3 = 12
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入</strong>
["UndergroundSystem","checkIn","checkOut","getAverageTime","checkIn","checkOut","getAverageTime","checkIn","checkOut","getAverageTime"]
[[],[10,"Leyton",3],[10,"Paradise",8],["Leyton","Paradise"],[5,"Leyton",10],[5,"Paradise",16],["Leyton","Paradise"],[2,"Leyton",21],[2,"Paradise",30],["Leyton","Paradise"]]

<strong>输出</strong>
[null,null,null,5.00000,null,null,5.50000,null,null,6.66667]

<strong>解释</strong>
UndergroundSystem undergroundSystem = new UndergroundSystem();
undergroundSystem.checkIn(10, "Leyton", 3);
undergroundSystem.checkOut(10, "Paradise", 8); // 乘客 10 "Leyton" -&gt; "Paradise" ，用时 8-3 = 5
undergroundSystem.getAverageTime("Leyton", "Paradise"); // 返回 5.00000 ，(5) / 1 = 5
undergroundSystem.checkIn(5, "Leyton", 10);
undergroundSystem.checkOut(5, "Paradise", 16); // 乘客 5 "Leyton" -&gt; "Paradise" ，用时 16-10 = 6
undergroundSystem.getAverageTime("Leyton", "Paradise"); // 返回 5.50000 ，(5 + 6) / 2 = 5.5
undergroundSystem.checkIn(2, "Leyton", 21);
undergroundSystem.checkOut(2, "Paradise", 30); // 乘客 2 "Leyton" -&gt; "Paradise" ，用时 30-21 = 9
undergroundSystem.getAverageTime("Leyton", "Paradise"); // 返回 6.66667 ，(5 + 6 + 9) / 3 = 6.66667
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= id, t &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= stationName.length, startStation.length, endStation.length &lt;= 10</code> 次</li>
	<li>所有字符串由大小写英文字母与数字组成</li>
	<li>总共最多调用 <code>checkIn</code>、<code>checkOut</code> 和 <code>getAverageTime</code> 方法 <code>2 * 10<sup>4 </sup></code></li>
	<li>与标准答案误差在 <code>10<sup>-5</sup></code> 以内的结果都被视为正确结果</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

我们用两个哈希表来存储数据，其中：

-   `ts`：存储乘客的 id 和乘客的进站时间和进站站点。其中键为乘客的 id，值为元组 `(t, stationName)`。
-   `d`：存储乘客的进站站点和出站站点，以及乘客的行程时间和行程次数。其中键为元组 `(startStation, endStation)`，值为元组 `(totalTime, count)`。

当乘客进站时，我们将乘客的 id 和进站时间和进站站点存入 `ts` 中，即 `ts[id] = (t, stationName)`。

当乘客出站时，我们从 `ts` 中取出乘客的进站时间和进站站点 `(t0, station)`，然后计算乘客的行程时间 $t - t_0$，并将乘客的行程时间和行程次数存入 `d` 中。

当我们要求某个乘客的平均行程时间时，我们从 `d` 中取出乘客的行程时间和行程次数 `(totalTime, count)`，然后计算平均行程时间 $totalTime / count$ 即可。

时间复杂度 $O(1)$，空间复杂度 $O(n)$。其中 $n$ 为乘客的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class UndergroundSystem:

    def __init__(self):
        self.ts = {}
        self.d = {}

    def checkIn(self, id: int, stationName: str, t: int) -> None:
        self.ts[id] = (t, stationName)

    def checkOut(self, id: int, stationName: str, t: int) -> None:
        t0, station = self.ts[id]
        x = self.d.get((station, stationName), (0, 0))
        self.d[(station, stationName)] = (x[0] + t - t0, x[1] + 1)

    def getAverageTime(self, startStation: str, endStation: str) -> float:
        x = self.d[(startStation, endStation)]
        return x[0] / x[1]


# Your UndergroundSystem object will be instantiated and called as such:
# obj = UndergroundSystem()
# obj.checkIn(id,stationName,t)
# obj.checkOut(id,stationName,t)
# param_3 = obj.getAverageTime(startStation,endStation)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class UndergroundSystem {
    private Map<Integer, Integer> ts = new HashMap<>();
    private Map<Integer, String> names = new HashMap<>();
    private Map<String, int[]> d = new HashMap<>();

    public UndergroundSystem() {

    }

    public void checkIn(int id, String stationName, int t) {
        ts.put(id, t);
        names.put(id, stationName);
    }

    public void checkOut(int id, String stationName, int t) {
        String key = names.get(id) + "-" + stationName;
        int[] v = d.getOrDefault(key, new int[2]);
        v[0] += t - ts.get(id);
        v[1]++;
        d.put(key, v);
    }

    public double getAverageTime(String startStation, String endStation) {
        String key = startStation + "-" + endStation;
        int[] v = d.get(key);
        return (double) v[0] / v[1];
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */
```

### **C++**

```cpp
class UndergroundSystem {
public:
    UndergroundSystem() {
    }

    void checkIn(int id, string stationName, int t) {
        ts[id] = {stationName, t};
    }

    void checkOut(int id, string stationName, int t) {
        auto [station, t0] = ts[id];
        auto key = station + "-" + stationName;
        auto [tot, cnt] = d[key];
        d[key] = {tot + t - t0, cnt + 1};
    }

    double getAverageTime(string startStation, string endStation) {
        auto [tot, cnt] = d[startStation + "-" + endStation];
        return (double) tot / cnt;
    }

private:
    unordered_map<int, pair<string, int>> ts;
    unordered_map<string, pair<int, int>> d;
};

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem* obj = new UndergroundSystem();
 * obj->checkIn(id,stationName,t);
 * obj->checkOut(id,stationName,t);
 * double param_3 = obj->getAverageTime(startStation,endStation);
 */
```

### **Go**

```go
type UndergroundSystem struct {
	ts map[int]pair
	d  map[station][2]int
}

func Constructor() UndergroundSystem {
	return UndergroundSystem{
		ts: make(map[int]pair),
		d:  make(map[station][2]int),
	}
}

func (this *UndergroundSystem) CheckIn(id int, stationName string, t int) {
	this.ts[id] = pair{t, stationName}
}

func (this *UndergroundSystem) CheckOut(id int, stationName string, t int) {
	p := this.ts[id]
	s := station{p.a, stationName}
	if _, ok := this.d[s]; !ok {
		this.d[s] = [2]int{t - p.t, 1}
	} else {
		this.d[s] = [2]int{this.d[s][0] + t - p.t, this.d[s][1] + 1}
	}

}

func (this *UndergroundSystem) GetAverageTime(startStation string, endStation string) float64 {
	s := station{startStation, endStation}
	return float64(this.d[s][0]) / float64(this.d[s][1])
}

type station struct {
	a string
	b string
}

type pair struct {
	t int
	a string
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * obj := Constructor();
 * obj.CheckIn(id,stationName,t);
 * obj.CheckOut(id,stationName,t);
 * param_3 := obj.GetAverageTime(startStation,endStation);
 */
```

### **...**

```

```

<!-- tabs:end -->
