package az.mapacademy.announcement_backend.Constant;

public class QueryConstants {
    public static final String GET_CITY_LIST_QUERY = "SELECT *FROM cities";
    public static final String GET_CATEGORY_LIST_QUERY = "SELECT *FROM categories";
    public static final String GET_ANNOUNCMENT_LIST_QUERY = """
            SELECT
            a.annoucnement_ID,
            a.Name,
            a.Description,
            a.announcement_number,
            a.PRICE,
            a.Phone_number,
            a.Sales_full_name,
            a.delivery,
            a.Category_id,
            a.Creadet_date,
            a.Modified_date,
            c.City_id ,
            c.NAME as city_name,
            ct.category_ID,
            ct.Name as category_name
            FROM annoucnements a
            LEFT JOIN cities c ON a.City_id = c.City_ID
            LEFT JOIN categories ct ON a.Category_id = ct.category_ID
            LIMIT ? OFFSET ?""";

    public static final String CREATE_ANNOUNCMENT_QUERY = """
            INSERT INTO annoucnements(Name,Description,announcement_number,Price,Phone_number,Sales_full_name,delivery,City_id,Category_id)
            VALUES(?,?,?,?,?,?,?,?,?)""";

    public static final String UPDATE_ANNOUNCMENT_QUERY = """
            UPDATE annoucnements
            
            SET
                Name = ?,
                Description = ?,
                Price = ?,
                sales_full_name = ?,
                delivery = ?
            WHERE annoucnement_ID= 1""";
    public static final String GET_ANNOUNCMENT_COUNT_QUERY = """
            (SELECT COUNT(*)  AS total_count FROM annoucnements);""";
    public static final String GET_ANNOUNCEMENT_BY_ID = """
            SELECT A.announcement_id,
                   A.name,
                   A.description,
                   A.announcement_number,
                   A.price,
                   A.phone_number,
                   A.sales_full_name,
                   A.delivery,
                   A.created_date,
                   A.modified_date,
                   C.city_id,
                   C.name as city_name,
                   ct.category_id,
                   ct.NAME as category_name
            FROM announcements a
                     LEFT JOIN cities c ON A.city_id = C.city_id
                     LEFT JOIN categories ct ON A.category_id = ct.category_id
            WHERE announcement_id = ?
            """;

}
