package info.cheremisin.social.network.constants;

public enum Gender {
    MALE(1),
    FEMALE(2),
    UNKNOWN(3);

    private final int gender;

    Gender(int sex) {
        gender = sex;
    }

    public static Gender getGenderById(Integer id) {
        if (id == null)
            return UNKNOWN;

        switch (id) {
            case 1:
                return MALE;
            case 2:
                return FEMALE;
            default:
                return UNKNOWN;
        }
    }

    public static int getGenderByName(String name) {
        if (name == null)
            return UNKNOWN.gender;

        switch (name) {
            case "Male":
                return MALE.gender;
            case "Female":
                return FEMALE.gender;
            default:
                return UNKNOWN.gender;
        }
    }
}
