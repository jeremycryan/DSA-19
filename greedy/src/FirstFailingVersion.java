public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {

        return firstBadVersionRange(-1, n, isBadVersion);

    }

    private static long firstBadVersionRange(long start, long end, IsFailingVersion isBadVersion) {

        if (end - start <= 1) return end;

        long test_version = (long)((end - start)/2) + start;

        if (isBadVersion.isFailingVersion(test_version)) {
            return firstBadVersionRange(start, test_version, isBadVersion);
        } else {
            return firstBadVersionRange(test_version, end, isBadVersion);
        }

    }
}
