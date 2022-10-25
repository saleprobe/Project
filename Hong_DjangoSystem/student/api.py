from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from .serializers import *


class StudentList(APIView):

    def get(self, request):
        model = Student.objects.all()
        serializer = StudentSerializer(model, many=True)
        return Response(serializer.data)

    def post(self, requset):
        serializer = StudentSerializer(data=requset.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class StudentDetail(APIView):

    def get(self, request, student_id):

        model = Student.objects.get(student_id=student_id)
        serializer = StudentSerializer(model)
        return Response(serializer.data)

    def put(self, request, student_id):

        model = Student.objects.get(student_id=student_id)

        serializer = StudentSerializer(model, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
