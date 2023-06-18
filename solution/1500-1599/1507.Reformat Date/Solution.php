class Solution {
    /**
     * @param String $date
     * @return String
     */
    function reformatDate($date) {
        $arr = explode(' ', $date);
        $months = [
            'Jan' => '01',
            'Feb' => '02',
            'Mar' => '03',
            'Apr' => '04',
            'May' => '05',
            'Jun' => '06',
            'Jul' => '07',
            'Aug' => '08',
            'Sep' => '09',
            'Oct' => '10',
            'Nov' => '11',
            'Dec' => '12',
        ];
        $year = $arr[2];
        $month = $months[$arr[1]];
        $day = intval($arr[0]);
        if ($day > 0 && $day < 10) {
            $day = '0' . $day;
        }
        return $year . '-' . $month . '-' . $day;
    }
}