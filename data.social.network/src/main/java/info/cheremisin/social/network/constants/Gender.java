package info.cheremisin.social.network.constants;

public enum Gender {
    Male(1), Female(2), Unknown(3);

    private int gender;

    Gender(int sex) {
        this.gender = sex;
    }

    public static Gender getGenderById(Integer id) {
        if(id == null) {
            return Unknown;
        }
        switch (id) {
            case 1:
                return Male;
            case 2:
                return Female;
            default:
                return Unknown;
        }
    }

    public static int getGenderByName(String name) {
        if(name == null) {
            return Unknown.gender;
        }
        switch (name) {
            case "Male":
                return Male.gender;
            case "Female":
                return Female.gender;
            default:
                return Unknown.gender;
        }
    }
}
