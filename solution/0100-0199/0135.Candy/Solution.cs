public class Solution {
    public int Candy(int[] ratings) {
        if (ratings.Length == 0) return 0;
        var result = 0;
        var previousRating = ratings[0];
        var previousCandy = 0;
        var downCount = 0;
        int? downCountThreshold = null;
        foreach (var rating in ratings)
        {
            if (rating == previousRating)
            {
                result += 1;
                previousCandy = 1;
                downCount = 1;
                downCountThreshold = null;
            }
            else if (rating > previousRating)
            {
                ++previousCandy;
                result += previousCandy;
                downCount = 1;
                downCountThreshold = null;
            }
            else
            {
                if (downCountThreshold == null)
                {
                    downCountThreshold = previousCandy - 1;
                }
                previousCandy = 1;
                result += downCount + (downCount > downCountThreshold.Value ? 1 : 0);
                ++downCount;
            }
            previousRating = rating;
            //System.Console.WriteLine("{0} {1}", previousCandy, result);
        }
        return result;
    }
}