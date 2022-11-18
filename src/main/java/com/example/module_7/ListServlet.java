package com.example.module_7;

import Entities.TodoItemEntity;
//import com.sun.tools.javac.comp.Todo;
import jakarta.persistence.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

@WebServlet(name = "ListServlet", value = "/ListServlet")
public class ListServlet extends HttpServlet {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();
    private static EntityTransaction transaction = entityManager.getTransaction();

    private static Scanner scanner = new Scanner(System.in);

    private static ArrayList<TodoItemEntity> todolist = new ArrayList<>();


    public void init(){
        //populate the arraylist todolist
        updateTodoList();

    }

    public static void updateTodoList(){
        todolist.clear();
        Collection response = entityManager.createQuery("SELECT e FROM TodoItemEntity e").getResultList();

        for(Iterator i = response.iterator(); i.hasNext();){
            TodoItemEntity e = (TodoItemEntity) i.next();
            todolist.add(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String output = "";
        output= output + "<html><body>";

        for (TodoItemEntity item : todolist) {
            output= output +"<p>"+ item.getItem() +"</p>";
        }
        System.out.println(output);
        output= output+ "</body></html>";
        out.println(output);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String newItem = request.getParameter("nitem");
        if(!newItem.isEmpty()) {
            try {
                transaction.begin();

                TodoItemEntity todo = new TodoItemEntity();
                todo.setItem(newItem);

                entityManager.persist(todo);

                transaction.commit();
            } finally {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
            }
        }

        updateTodoList();
        response.sendRedirect(request.getContextPath()+"/TodoServlet");
    }

    public void destroy() {
    }
}
