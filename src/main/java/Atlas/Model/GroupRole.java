package Atlas.Model;

public class GroupRole {
    private int role_id;
    private int group_id;
    private int user_id;
    private boolean can_view;
    private boolean can_edit;
    private boolean is_admin;

    
    public GroupRole(){
    }

    public GroupRole(int role_id, int group_id, int user_id){
        this.role_id = role_id;
        this.group_id = group_id;
        this.user_id =user_id;
    }

    public GroupRole(int role_id, int group_id, int user_id, boolean can_view, boolean can_edit, boolean is_admin){
        this.role_id = role_id;
        this.group_id = group_id;
        this.user_id = user_id;
        this.can_edit = can_edit;
        this.can_view = can_view;
        this.is_admin = is_admin;
    }

    public int getGroupId() {
        return group_id;
    }
    public void setGroupId(int group_id) {
        this.group_id = group_id;
    }
    public int getRoleId() {
        return role_id;
    }
    public void setRoleId(int role_id) {
        this.role_id = role_id;
    }
    public int getUserId() {
        return user_id;
    }
    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
}
