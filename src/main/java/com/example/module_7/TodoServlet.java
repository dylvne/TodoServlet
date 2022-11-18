package com.example.module_7;

import Entities.TodoItemEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@WebServlet(name = "TodoServlet", value = "/TodoServlet")
public class TodoServlet extends HttpServlet {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();
    private static EntityTransaction transaction = entityManager.getTransaction();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<TodoItemEntity> todolist = new ArrayList<>();

        Collection res = entityManager.createQuery("SELECT e FROM TodoItemEntity e").getResultList();

        for(Iterator i = res.iterator(); i.hasNext();){
            TodoItemEntity e = (TodoItemEntity) i.next();
            todolist.add(e);
        }

        request.setAttribute("todolist", todolist);
        request.getRequestDispatcher("todo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            transaction.begin();
            TodoItemEntity target = entityManager.find(TodoItemEntity.class,Integer.parseInt(request.getParameter("ritem")));
            entityManager.remove(target);

            transaction.commit();
        } finally {
            if(transaction.isActive()){
                transaction.rollback();
            }
        }

        response.sendRedirect(request.getContextPath()+"/TodoServlet");
    }
}
