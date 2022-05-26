SELECT x,
         y,
         z,
         if(x + y > z
        AND x + z > y
        AND y + z > x, "Yes", "No") AS triangle
FROM triangle
