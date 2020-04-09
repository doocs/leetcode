// https://leetcode.com/problems/rectangle-area/

using System;

public partial class Solution
{
    public int ComputeArea(int A, int B, int C, int D, int E, int F, int G, int H)
    {
        var area = (long)(C - A) * (D - B) + (G - E) * (H - F);
        var overlapA = Math.Max(A, E);
        var overlapB = Math.Max(B, F);
        var overlapC = Math.Min(C, G);
        var overlapD = Math.Min(D, H);
        if (overlapA < overlapC && overlapB < overlapD)
        {
            area -= (overlapC - overlapA) * (overlapD - overlapB);
        }
        return (int)area;
    }
}