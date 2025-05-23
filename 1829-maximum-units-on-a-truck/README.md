<h2><a href="https://leetcode.com/problems/maximum-units-on-a-truck">Maximum Units on a Truck</a></h2> <img src='https://img.shields.io/badge/Difficulty-Easy-brightgreen' alt='Difficulty: Easy' /><hr><p>You are assigned to put some amount of boxes onto <strong>one truck</strong>. You are given a 2D array <code>boxTypes</code>, where <code>boxTypes[i] = [numberOfBoxes<sub>i</sub>, numberOfUnitsPerBox<sub>i</sub>]</code>:</p>

<ul>
	<li><code>numberOfBoxes<sub>i</sub></code> is the number of boxes of type <code>i</code>.</li>
	<li><code>numberOfUnitsPerBox<sub>i</sub></code><sub> </sub>is the number of units in each box of the type <code>i</code>.</li>
</ul>

<p>You are also given an integer <code>truckSize</code>, which is the <strong>maximum</strong> number of <strong>boxes</strong> that can be put on the truck. You can choose any boxes to put on the truck as long as the number&nbsp;of boxes does not exceed <code>truckSize</code>.</p>

<p>Return <em>the <strong>maximum</strong> total number of <strong>units</strong> that can be put on the truck.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
<strong>Output:</strong> 8
<strong>Explanation:</strong> There are:
- 1 box of the first type that contains 3 units.
- 2 boxes of the second type that contain 2 units each.
- 3 boxes of the third type that contain 1 unit each.
You can take all the boxes of the first and second types, and one box of the third type.
The total number of units will be = (1 * 3) + (2 * 2) + (1 * 1) = 8.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
<strong>Output:</strong> 91
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= boxTypes.length &lt;= 1000</code></li>
	<li><code>1 &lt;= numberOfBoxes<sub>i</sub>, numberOfUnitsPerBox<sub>i</sub> &lt;= 1000</code></li>
	<li><code>1 &lt;= truckSize &lt;= 10<sup>6</sup></code></li>
</ul>
