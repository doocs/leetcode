class Solution:
    def reformatDate(self, date: str) -> str:
        months = [
            "Jan",
            "Feb",
            "Mar",
            "Apr",
            "May",
            "Jun",
            "Jul",
            "Aug",
            "Sep",
            "Oct",
            "Nov",
            "Dec",
        ]
        mapper = {v: str(k + 1) for k, v in enumerate(months)}
        split = date.split(' ')
        year = split[2]
        month = mapper.get(split[1])
        day = split[0][: len(split[0]) - 2]
        return year + '-' + month.zfill(2) + '-' + day.zfill(2)
