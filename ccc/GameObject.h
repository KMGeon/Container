#pragma once
#include <Windows.h>
#include <string>


class GameObject
{
public:
	GameObject();
	~GameObject();

	 virtual void tick();
	 virtual void move(COORD _pos);
	 void checkCollision();
	 void draw();
	 virtual void die(); // isAlive�� 0���� ����
	 int isAlive; //����ִ��� �˷��ִº���, 0�̸� ��� ������ ����
protected:
	COORD pos; //���� ��ǥ
	std::string image; //������Ʈ�� �̹���, ȭ�鿡 �̰� �׷���

	 virtual void OnCollision(); //�浹�� �Ͼ�� �θ��� �Լ�, �浹�� ���� ���� ���� ��ӹ��� ���� Ŭ������ OnCollision�Լ��� �����ϸ� �˴ϴ�.
};

