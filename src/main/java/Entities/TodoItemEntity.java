package Entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "todoitem", schema = "todoDB", catalog = "")
public class TodoItemEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "item")
    private String item;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItemEntity that = (TodoItemEntity) o;
        return id == that.id && Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item);
    }
}
