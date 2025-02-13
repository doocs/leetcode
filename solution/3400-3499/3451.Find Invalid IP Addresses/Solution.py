import pandas as pd


def find_invalid_ips(logs: pd.DataFrame) -> pd.DataFrame:
    def is_valid_ip(ip: str) -> bool:
        octets = ip.split(".")
        if len(octets) != 4:
            return False
        for octet in octets:
            if not octet.isdigit():
                return False
            value = int(octet)
            if not 0 <= value <= 255 or octet != str(value):
                return False
        return True

    logs["is_valid"] = logs["ip"].apply(is_valid_ip)
    invalid_ips = logs[~logs["is_valid"]]
    invalid_count = invalid_ips["ip"].value_counts().reset_index()
    invalid_count.columns = ["ip", "invalid_count"]
    result = invalid_count.sort_values(
        by=["invalid_count", "ip"], ascending=[False, False]
    )
    return result
